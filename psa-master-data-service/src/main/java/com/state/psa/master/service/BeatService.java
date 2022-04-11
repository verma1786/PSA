package com.state.psa.master.service;

import com.state.psa.master.model.entity.Beat;
import com.state.psa.master.model.entity.State;

import java.util.List;
import java.util.Optional;

public interface BeatService {
    public List<Beat> findAll(Integer psId, Integer langId);
    public Optional<Beat> findByBeatId(Integer psId, Integer langId);
}