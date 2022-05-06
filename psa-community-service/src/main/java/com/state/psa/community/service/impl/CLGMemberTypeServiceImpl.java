package com.state.psa.community.service.impl;

import com.state.psa.community.model.entity.CLGMemberType;
import com.state.psa.community.repository.CLGMemberTypeRepository;
import com.state.psa.community.service.CLGMemberTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CLGMemberTypeServiceImpl implements CLGMemberTypeService {
    @Autowired
    CLGMemberTypeRepository clgMemberTypeRepository;

    @Autowired
    private CacheManager cacheManager;


    @Override
    public List<CLGMemberType> findAll() {
        Cache cache = cacheManager.getCache("stateCache");
        System.out.println("CHACHE _-----------------------------------"+cache.get(5).get());
        return clgMemberTypeRepository.findAll();
    }
}
