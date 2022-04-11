package com.state.psa.master.service;

import com.state.psa.master.model.entity.Occupation;

import java.util.List;
import java.util.Optional;

public interface OccupationService {
    public List<Occupation> findAll();
    public Optional<Occupation> findByOccupationId(Integer occupationId, Integer langId);
}
