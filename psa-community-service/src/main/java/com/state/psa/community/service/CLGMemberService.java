package com.state.psa.community.service;

import com.state.psa.community.model.entity.CLGMemberDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CLGMemberService {
    public void save(CLGMemberDetails clgMemberDetails);
    public Long saveMemberDetailWithFile(CLGMemberDetails clgMemberDetails,MultipartFile file);
    public Long updateMemberDetailWithFile(CLGMemberDetails clgMemberDetails,MultipartFile file);
    public CLGMemberDetails findById(Long id);
    public List<CLGMemberDetails> getAllMemberDetails(Integer pageNo, Integer pageSize, String sortBy);
    public String upload(MultipartFile file,String fileId);
    public String update(MultipartFile file,String fileId);
    public void deleteMember(long id);
}
