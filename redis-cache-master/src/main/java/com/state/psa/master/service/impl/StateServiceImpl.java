package com.state.psa.master.service.impl;

import com.state.psa.master.repository.StateRepository;
import com.state.psa.master.service.StateService;
import com.state.psa.master.model.entity.State;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;


    @Override
    @Transactional
    @Cacheable("states")
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    @Transactional
    @Cacheable(value = "state", key = "#stateId")
    public Optional<State> findById(Integer stateId)  {
        return stateRepository.findById(stateId);
    }
}
