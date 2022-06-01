package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.District;
import com.state.psa.master.repository.DistrictRepository;
import com.state.psa.master.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;


    @Override
    @Transactional
    @Cacheable("districts")
    public List<District> findAll(Integer stateId) {
        return districtRepository.findAll(stateId);
    }

    @Override
    @Transactional
    @Cacheable(value = "district", key = "#districtId")
    public Optional<District> findById(Integer districtId)  {
        return districtRepository.findById(districtId);
    }
}
