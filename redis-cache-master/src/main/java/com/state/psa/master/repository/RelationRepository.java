package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RelationRepository extends JpaRepository<Relation,Integer> {

    @Query(value="select * from m_relation_type where LANG_CD =99 and RECORD_STATUS<> 'D' and RELATION_TYPE_CD !=0 " ,nativeQuery=true)
    public List<Relation> findAll();

    @Query(value="select * from m_relation_type where LANG_CD =99 and RECORD_STATUS<> 'D' and RELATION_TYPE_CD =:relationId" ,nativeQuery=true)
    public Optional<Relation> findById(Integer relationId);
}
