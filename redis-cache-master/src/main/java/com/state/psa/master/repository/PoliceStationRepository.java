package com.state.psa.master.repository;

import com.state.psa.master.model.entity.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliceStationRepository extends JpaRepository<PoliceStation,Integer> {

    @Query(value="select * from m_police_station where LANG_CD =99 and RECORD_STATUS<> 'D' and DISTRICT_CD =:districtId and PS_CD !=0 ORDER BY PS ASC " ,nativeQuery=true)
    public List<PoliceStation> findAll(Integer districtId);


    @Query(value="select * from m_police_station where LANG_CD =99 and RECORD_STATUS<> 'D' and PS_CD =:psId" ,nativeQuery=true)
    public Optional<PoliceStation> findById(Integer psId);

    List<PoliceStation> findByDistrictIdAndLangIdOrderByPsAsc(Integer districtId, Integer langId);

}
