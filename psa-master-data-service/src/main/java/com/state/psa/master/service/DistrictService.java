package com.state.psa.master.service;

import com.state.psa.master.model.entity.District;

import java.util.List;

public interface DistrictService {
    public List<District> findAll();
    public List<District> findById(Integer stateId);

}
