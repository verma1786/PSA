package com.state.psa.community.service;

import com.state.psa.community.model.dto.request.CLGMemberRequest;
import com.state.psa.community.model.dto.response.CLGMemberResponse;
import com.state.psa.community.model.entity.CLGMemberDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface CLGMemberService {
    public void save(CLGMemberDetails clgMemberDetails);
    public void saveMemberDetailWithFile(CLGMemberDetails clgMemberDetails,MultipartFile file);
    public CLGMemberDetails findById(Long id);
    public List<CLGMemberDetails> getAllMemberDetails(Integer pageNo, Integer pageSize, String sortBy);
    public String upload(MultipartFile file);

    public void deleteMember(long id);
}
