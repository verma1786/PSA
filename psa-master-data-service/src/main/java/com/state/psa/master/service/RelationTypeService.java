package com.state.psa.master.service;

import com.state.psa.master.model.entity.RelationType;

import java.util.List;

public interface RelationTypeService {
    List<RelationType> findByLangIdOrderByPsAsc(Integer langId);
}
