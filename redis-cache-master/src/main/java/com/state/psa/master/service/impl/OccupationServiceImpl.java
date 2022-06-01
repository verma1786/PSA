package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.Relation;
import com.state.psa.master.repository.RelationRepository;
import com.state.psa.master.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationRepository relationRepository;

    @Override
    @Transactional
    @Cacheable("relations")
    public List<Relation> findAll() {
        return relationRepository.findAll();
    }

    @Override
    @Transactional
    @Cacheable(value = "relation", key = "#relationId")
    public Optional<Relation> findById(Integer relationId) {
        return relationRepository.findById(relationId);
    }
}
