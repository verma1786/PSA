package com.state.psa.master.service.impl;


import com.state.psa.master.model.entity.PoliceStation;
import com.state.psa.master.model.entity.RelationType;
import com.state.psa.master.repository.PoliceStationRepository;
import com.state.psa.master.repository.RelationTypeRepository;
import com.state.psa.master.service.PoliceStationService;
import com.state.psa.master.service.RelationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RelationTypeServiceImpl implements RelationTypeService {
    @Autowired
    private RelationTypeRepository relationTypeRepository;

    @Override
    public List<RelationType> findByLangIdOrderByPsAsc(Integer langId) {
      return relationTypeRepository.findByLangIdOrderByRelationTypeAsc(langId);
    }


}
