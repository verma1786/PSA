package com.state.psa.master.repository;

import com.state.psa.master.model.entity.PoliceStation;
import com.state.psa.master.model.entity.RelationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelationTypeRepository extends JpaRepository<RelationType, Integer> {

   @Query(value="select * from m_relation_type where LANG_CD =:langId and RECORD_STATUS<> 'D' and RELATION_TYPE_CD != 0 order by RELATION_TYPE asc" ,nativeQuery=true)
           List<RelationType> findByLangIdOrderByRelationTypeAsc(Integer langId);
}
