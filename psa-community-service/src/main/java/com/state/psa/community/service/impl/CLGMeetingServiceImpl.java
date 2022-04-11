package com.state.psa.community.service.impl;

import com.state.psa.community.model.entity.CLGMeetingDetails;
import com.state.psa.community.model.entity.CLGMemberDetails;
import com.state.psa.community.repository.CLGMeetingRepository;
import com.state.psa.community.repository.CLGMemberRepository;
import com.state.psa.community.service.CLGMeetingService;
import com.state.psa.community.service.CLGMemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CLGMeetingServiceImpl implements CLGMeetingService {
    @Autowired
    CLGMeetingRepository clgMeetingRepository;
    @Override
    public Long save(CLGMeetingDetails clgMeetingDetails) {
       return clgMeetingRepository.save(clgMeetingDetails).getId();
    }


}
