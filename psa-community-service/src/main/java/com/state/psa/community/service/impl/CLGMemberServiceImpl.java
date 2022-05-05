package com.state.psa.community.service.impl;

import com.state.psa.community.exception.EntityNotFoundException;
import com.state.psa.community.exception.ResourceNotFoundException;
import com.state.psa.community.model.State;
import com.state.psa.community.model.dto.LoadFile;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.repository.CLGMemberRepository;
import com.state.psa.community.service.CLGMemberService;
import com.state.psa.community.utils.UUIDGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bouncycastle.its.asn1.SequenceOfPsidGroupPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

import javax.xml.bind.SchemaOutputResolver;
import java.util.*;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@PropertySource("classpath:/messages/business/uri.properties")
@Service
public class CLGMemberServiceImpl implements CLGMemberService {

    @Value("${get.states.url}")
    private String GET_STATES_ENDPOINT_URL;

    @Value("${get.file.url}")
    private String GET_FILE_ENDPOINT_URL ;

    @Value("${download.file.url}")
    private String DOWNLOAD_FILE_ENDPOINT_URL;

    @Value("${save.file.url}")
    private String SAVE_FILE_ENDPOINT_URL ;

    @Value("${update.file.url}")
    private String UPDATE_FILE_URL;

    @Value("${delete.file.url}")
    private String DELETE_FILE_PATH;

    @Value("${get.file.list.url}")
    private String GET_FILES_ENDPOINT_URL ;

    @Autowired
    CLGMemberRepository clgMemberRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public void save(CLGMemberDetails clgMemberDetails) {
         clgMemberRepository.save(clgMemberDetails);
    }

    @Override
    public Long saveMemberDetailWithFile(CLGMemberDetails clgMemberDetails, MultipartFile file) {
        String fId= String.valueOf(UUIDGenerator.generateType1UUID());
        System.out.println("FILE ID : "+fId);
        String fileId=uploadFile(file,fId);
        clgMemberDetails.setFileRefId(fId);
        clgMemberDetails.setFileId(fileId);
       return clgMemberRepository.save(clgMemberDetails).getId();
    }
    @Override
    public Long updateMemberDetailWithFile(CLGMemberDetails clgMemberDetails, MultipartFile file) {
        String newId=update(file,clgMemberDetails.getFileId());
        System.out.println("NEW FILE ID : "+newId);
        System.out.println("OLD FILE ID : "+clgMemberDetails.getFileId());
        clgMemberDetails.setFileRefId(clgMemberDetails.getFileId());
        clgMemberDetails.setFileId(newId);
        return clgMemberRepository.save(clgMemberDetails).getId();
    }

    @Override
    public CLGMemberDetails findById(Long id) {
       return clgMemberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(CLGMemberDetails.class, "clgId", id.toString()));
     }

    @Override
    public List<CLGMemberDetails> getAllMemberDetails(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
       // Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<CLGMemberDetails> pagedResult = clgMemberRepository.findAll(paging);

        List<CLGMemberDetails> memberList= pagedResult.getContent();
        List<CLGMemberDetails> memberList1=new ArrayList<>();
        for(CLGMemberDetails mem:memberList) {
          LoadFile file=  getLoadFile(mem.getFileId());
            mem.setFile(file.getFile());
            memberList1.add(mem);
        }
        /*List<String> idList=memberList.stream().map(x -> x.getFileId()).collect(Collectors.toList());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++ : "+idList);
        List<LoadFile> fList=getFiles(idList);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++ : "+fList.size());
       for(LoadFile f:fList){
           System.out.println("File : "+f.toString());
       }
        List<CLGMemberDetails> a=  pagedResult.stream().collect(Collectors.toList());*/
        if(pagedResult.hasContent()) {
            return memberList1;//pagedResult.getContent();
        } else {
            return new ArrayList<CLGMemberDetails>();
        }
    }

    @Override
    public String upload(MultipartFile file,String fileId) {
        return uploadFile(file,fileId);
    }
    @Override
    public String update(MultipartFile file,String fileId) {
        return updateFile(file,fileId);
    }

    public byte[] getFile(String fileId){
    //restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
    //LoadFile loadFile = restTemplate.getForObject(DOWNLOAD_FILE_ENDPOINT_URL+fileId, LoadFile.class);
    byte[] imageBytes = restTemplate.getForObject(DOWNLOAD_FILE_ENDPOINT_URL+fileId, byte[].class);
    System.out.println(imageBytes.toString());
    return imageBytes;
}
    public List<State> getStates() {
        ResponseEntity<List<State>> responseEntity = restTemplate.exchange(GET_STATES_ENDPOINT_URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<State>>() {});
        return responseEntity.getBody();
    }


    public String uploadFile(MultipartFile file,String fileId) {
        Resource resource = file.getResource();
        LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", resource);
        parts.add("fileId", fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(SAVE_FILE_ENDPOINT_URL, httpEntity, String.class);
        return stringResponseEntity.getBody();

    }

    public String updateFile(MultipartFile file,String fileId) {
        Resource resource = file.getResource();
        LinkedMultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", resource);
        parts.add("fileId", fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(parts, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(UPDATE_FILE_URL, httpEntity, String.class);
        return stringResponseEntity.getBody();

    }
    public LoadFile getLoadFile(String id){
        return restTemplate.getForObject(GET_FILE_ENDPOINT_URL+id, LoadFile.class);
    }
    public List<LoadFile> getFiles(List<String> ids){
        ResponseEntity<List<LoadFile>> responseEntity = restTemplate.exchange(GET_FILES_ENDPOINT_URL+ids, HttpMethod.GET, null, new ParameterizedTypeReference<List<LoadFile>>() {});
        return responseEntity.getBody();
    }

    @Override
    public void deleteMember(long id){
        CLGMemberDetails clgMemberDetails=null;
        clgMemberDetails = clgMemberRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid CLG Member Id:" + id));
        deleteFile(clgMemberDetails.getFileId());
        clgMemberRepository.delete(clgMemberDetails);
    }

    private void deleteFile(String id) {
        Map < String, String > params = new HashMap< String, String >();
        params.put("id", id);
        restTemplate.delete(DELETE_FILE_PATH, params);

    }
}
