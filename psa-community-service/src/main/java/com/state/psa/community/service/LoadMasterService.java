package com.state.psa.community.service;

import com.state.psa.community.model.State;
import com.state.psa.community.model.entity.CLGMemberDetails;

import java.util.List;


public interface LoadMasterService {
    public State findById(Integer stateId);

    List<CLGMemberDetails> findAllCLGMemberDetails();
}
