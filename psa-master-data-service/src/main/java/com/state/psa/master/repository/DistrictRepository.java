package com.state.psa.master.repository;

import com.state.psa.master.model.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
    public List<District> findDistrictByStateId(Integer stateId);
}
