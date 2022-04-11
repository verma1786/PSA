package com.state.psa.master.service.impl;


import com.state.psa.master.model.entity.PoliceStation;
import com.state.psa.master.repository.PoliceStationRepository;
import com.state.psa.master.service.DistrictService;
import com.state.psa.master.service.PoliceStationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PoliceStationServiceImpl implements PoliceStationService {
    @Autowired
    private PoliceStationRepository policeStationRepository;

    @Override
    public List<PoliceStation> findPoliceStations(Integer districtId,Integer langId) {
      return policeStationRepository.findByDistrictIdAndLangIdOrderByPsAsc(districtId,langId);
    }


}
