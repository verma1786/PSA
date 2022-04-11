package com.state.psa.master.repository;

import com.state.psa.master.model.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation, Integer> {
     List<PoliceStation> findByDistrictIdAndLangIdOrderByPsAsc(Integer districtId, Integer langId);
}
