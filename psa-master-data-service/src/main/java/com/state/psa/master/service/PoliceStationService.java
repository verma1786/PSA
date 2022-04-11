package com.state.psa.master.service;

import com.state.psa.master.model.entity.PoliceStation;
import java.util.List;

public interface PoliceStationService {
     public List<PoliceStation> findPoliceStations(Integer districtId,Integer langId);
}
