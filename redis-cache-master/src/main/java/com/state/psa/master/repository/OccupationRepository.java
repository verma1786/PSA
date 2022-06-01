package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation,Integer> {

    @Query(value="select * from m_occupation where LANG_CD =99 and RECORD_STATUS<> 'D' and OCCUPATION_CD !=0 " ,nativeQuery=true)
    List<Occupation> findAll();

    @Query(value="select * from m_occupation where LANG_CD =99 and RECORD_STATUS<> 'D' and OCCUPATION_CD=:occupationId" ,nativeQuery=true)
    Optional<Occupation> findById(Integer occupationId);
}
