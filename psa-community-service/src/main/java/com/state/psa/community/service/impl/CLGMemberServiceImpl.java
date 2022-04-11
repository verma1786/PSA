package com.state.psa.community.service.impl;

import com.state.psa.community.exception.ResourceNotFoundException;
import com.state.psa.community.model.State;
import com.state.psa.community.model.dto.LoadFile;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.repository.CLGMemberRepository;
import com.state.psa.community.service.CLGMemberService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CLGMemberServiceImpl implements CLGMemberService {

    @Value("${state.url}")
    private String restUrl;

    @Value("${read.file.url}")
    private String getFileUrlById;

    @Value("${read.file.save.url}")
    private String getFileSaveUrl;

    @Value("${get.file.list.url}")
    private String getFileListUrl;

    @Autowired
    CLGMemberRepository clgMemberRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void save(CLGMemberDetails clgMemberDetails) {
         clgMemberRepository.save(clgMemberDetails);
    }

    @Override
    public void saveMemberDetailWithFile(CLGMemberDetails clgMemberDetails, MultipartFile file) {
        System.out.println("clgMemberDetails ========================== " + clgMemberDetails);
        String fileId=uploadFile(file);
        System.out.println("File ID : "+fileId);
        clgMemberDetails.setFileId(fileId);
        clgMemberRepository.save(clgMemberDetails);
    }


    @Override
    public CLGMemberDetails findById(Long id) {
       return clgMemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid CLG Member Id:" + id));
     }

    @Override
    public List<CLGMemberDetails> getAllMemberDetails(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
       // Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<CLGMemberDetails> pagedResult = clgMemberRepository.findAll(paging);

        List<CLGMemberDetails> memberList= pagedResult.getContent();
        List<String> idList=memberList.stream().map(x -> x.getFileId()).collect(Collectors.toList());
        List<LoadFile> fList=getFiles(idList);

        List<CLGMemberDetails> a=  pagedResult.stream().collect(Collectors.toList());
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<CLGMemberDetails>();
        }
    }

    @Override
    public String upload(MultipartFile file) {
        return uploadFile(file);
    }

    @Override
    public void deleteMember(long id){
        CLGMemberDetails clgMemberDetails = clgMemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid CLG Member Id:" + id));
        clgMemberRepository.delete(clgMemberDetails);
    }

    public byte[] getFile(String fileId){
    //restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    //LoadFile loadFile = restTemplate.getForObject(getFileUrlById+fileId, LoadFile.class);
    byte[] imageBytes = restTemplate.getForObject(getFileUrlById+fileId, byte[].class);
    System.out.println(imageBytes.toString());
    return imageBytes;
}
    public List<State> getStates() {
        ResponseEntity<List<State>> responseEntity = restTemplate.exchange(restUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<State>>() {});
        return responseEntity.getBody();
    }


    public String uploadFile(MultipartFile file) {
        Resource invoicesResource = file.getResource();
        LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", invoicesResource);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(getFileSaveUrl, httpEntity, String.class);
        return stringResponseEntity.getBody();

    }
    public List<LoadFile> getFiles(List<String> ids){
        ResponseEntity<List<LoadFile>> responseEntity = restTemplate.exchange(getFileListUrl+ids, HttpMethod.GET, null, new ParameterizedTypeReference<List<LoadFile>>() {});
        return responseEntity.getBody();
    }
}
