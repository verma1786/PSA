package com.state.psa.master.repository;

import com.state.psa.master.model.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
    @Query(value="select * from m_state where LANG_CD =6 and RECORD_STATUS<> 'D' " ,nativeQuery=true)
    public List<State> findAll();
    @Query(value="select * from m_state where LANG_CD =6 and RECORD_STATUS<> 'D' and state_cd=:stateId " ,nativeQuery=true)
    public Optional<State> findByStateId(Integer stateId);
}
