package com.state.psa.master.service;

import com.state.psa.master.model.entity.Gender;

import java.util.List;
import java.util.Optional;

public interface GenderService {
    List<Gender> findAll();
    Optional<Gender> findById(Integer genderId) ;
}
