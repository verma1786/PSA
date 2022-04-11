package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.Beat;
import com.state.psa.master.repository.BeatRepository;
import com.state.psa.master.service.BeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeatServiceImpl implements BeatService {
    @Autowired
    private BeatRepository beatRepository;

    @Override
    public List<Beat> findAll(Integer psId,Integer langId) {
        return beatRepository.findByPsId(psId,langId);
    }

    @Override
    public Optional<Beat> findByBeatId(Integer beatId,Integer langId) {
        return beatRepository.findByBeatId(beatId, langId); //stateRepository.findById(stateId);
    }
}
