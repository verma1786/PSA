package com.state.psa.master.service.impl;

import com.state.psa.master.model.entity.Nationality;
import com.state.psa.master.repository.NationalityRepository;
import com.state.psa.master.service.NationalityService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NationalityServiceImpl implements NationalityService {
    @Autowired
    NationalityRepository nationalityRepository;

    @Override
    public List<Nationality> findAll() {
        return nationalityRepository.findAll();
    }

    @Override
    public Optional<Nationality> findById(Integer id) {
        return nationalityRepository.findById(id);
    }
}
