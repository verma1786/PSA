package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.District;
import com.state.psa.master.model.entity.State;
import com.state.psa.master.repository.DistrictRepository;
import com.state.psa.master.repository.StateRepository;
import com.state.psa.master.service.DistrictService;
import com.state.psa.master.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }
    @Override
    public List<District> findById(Integer stateId) {
        return districtRepository.findDistrictByStateId(stateId);
    }


}
