package com.state.psa.master.repository;

import com.state.psa.master.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {

    @Query(value="select * from M_DISTRICT where LANG_CD =99 and RECORD_STATUS<> 'D' and DISTRICT_CD !=0 AND STATE_CD=:stateId " ,nativeQuery=true)
    public List<District> findAll(Integer stateId);

    @Query(value="select * from M_DISTRICT where LANG_CD =99 and RECORD_STATUS<> 'D' and DISTRICT_CD=:districtId" ,nativeQuery=true)
    public Optional<District> findById(Integer districtId);
}
