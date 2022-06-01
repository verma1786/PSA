package com.state.psa.master.service;

import java.util.List;
import java.util.Optional;

public interface RelationService<T> {
    List<T> findAll();
    Optional<T> findById(Integer relationId) ;
}
