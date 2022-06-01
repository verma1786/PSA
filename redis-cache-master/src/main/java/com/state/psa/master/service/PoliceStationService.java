package com.state.psa.master.service;

import com.state.psa.master.model.entity.District;

import java.util.List;
import java.util.Optional;

public interface DistrictService {
    List<District> findAll(Integer stateId);
    Optional<District> findById(Integer districtId) ;
}
