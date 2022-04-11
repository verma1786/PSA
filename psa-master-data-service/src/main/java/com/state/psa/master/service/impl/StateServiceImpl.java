package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.State;
import com.state.psa.master.repository.StateRepository;
import com.state.psa.master.service.StateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = "stateCache")
public class StateServiceImpl implements StateService {
    @Autowired
    private StateRepository stateRepository;

//    @Cacheable("states")
    @Cacheable
    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }


//    @Cacheable(cacheNames="state", key="#stateId")
    @Cacheable
    @Override
    public Optional<State> findById(Integer stateId) {
        return stateRepository.findByStateId(stateId);
    }

}
