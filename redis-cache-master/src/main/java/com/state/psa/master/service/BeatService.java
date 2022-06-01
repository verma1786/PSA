package com.state.psa.master.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T> {
    List<T> findAll();
    Optional<T> findById(Integer relationId) ;
}
