package com.state.psa.master.service;

import com.state.psa.master.model.entity.State;

import java.util.List;
import java.util.Optional;

public interface StateService {
    List<State> findAll();
    Optional<State> findById(Integer stateId) ;
}
