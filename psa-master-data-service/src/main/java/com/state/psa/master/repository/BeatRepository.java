package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Beat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BeatRepository extends JpaRepository<Beat, Integer> {
    @Query(value="select * from m_ps_beat where LANG_CD =:langId and RECORD_STATUS<> 'D' and ps_cd=:psId " ,nativeQuery=true)
    List<Beat> findByPsId(Integer psId, Integer langId);

    @Query(value="select * from m_ps_beat where LANG_CD =:langId and RECORD_STATUS<> 'D' and beat_cd=:beatId " ,nativeQuery=true)
    Optional<Beat> findByBeatId(Integer beatId,Integer langId);
}
