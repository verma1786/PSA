package com.state.psa.master.repository;

import com.state.psa.master.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {

    @Query(value="select * from M_STATE where LANG_CD =99 and RECORD_STATUS<> 'D' AND STATE_CD !=0 " ,nativeQuery=true)
    public List<State> findAll();

    @Query(value="select * from M_STATE where LANG_CD =99 and RECORD_STATUS<> 'D' and STATE_CD=:stateId" ,nativeQuery=true)
    public Optional<State> findById(Integer stateId);
}
