package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Beat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeatRepository extends JpaRepository<Beat,Integer> {

    @Query(value="select * from m_ps_beat where LANG_CD =99 and RECORD_STATUS<> 'D' and ps_cd=:psId AND BEAT_CD !=0" ,nativeQuery=true)
    List<Beat> findAll(Integer psId);

    @Query(value="select * from m_ps_beat where LANG_CD =99 and RECORD_STATUS<> 'D' and beat_cd=:beatId " ,nativeQuery=true)
    Optional<Beat> findById(Integer beatId);
}
