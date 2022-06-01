package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.Occupation;
import com.state.psa.master.repository.OccupationRepository;
import com.state.psa.master.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OccupationServiceImpl implements GenericService {

    @Autowired
    private OccupationRepository occupationRepository;

    @Override
    @Transactional
    @Cacheable("occupations")
    public List<Occupation> findAll() {
        return occupationRepository.findAll();
    }

    @Override
    @Transactional
    @Cacheable(value = "occupation", key = "#occupationId")
    public Optional<Occupation> findById(Integer relationId) {
        return occupationRepository.findById(relationId);
    }
}
