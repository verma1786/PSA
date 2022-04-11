package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.Occupation;
import com.state.psa.master.repository.OccupationRepository;
import com.state.psa.master.repository.StateRepository;
import com.state.psa.master.service.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OccupationServiceImpl implements OccupationService {

    @Autowired
    private OccupationRepository occupationRepository;

    @Override
    public List<Occupation> findAll() {
        return occupationRepository.findAll();
    }

    @Override
    public Optional<Occupation> findByOccupationId(Integer occupationId, Integer langId) {
        return occupationRepository.findByOccupationIdAndLangId(occupationId,langId);
    }
}
