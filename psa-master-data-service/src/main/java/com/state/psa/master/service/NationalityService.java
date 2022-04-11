package com.state.psa.master.service;

import com.state.psa.master.model.entity.Nationality;

import java.util.List;
import java.util.Optional;

public interface NationalityService {
       public List<Nationality> findAll();
       public Optional<Nationality> findById(Integer id);
}
