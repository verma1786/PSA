package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenderRepository extends JpaRepository<Gender,Integer> {
    String a=null;
    @Query(value="select * from M_GENDER where LANG_CD =99 and RECORD_STATUS<> 'D' and GENDER_CD !=0 ORDER BY GENDER DESC" ,nativeQuery=true)
    public List<Gender> findAll();

    @Query(value="select * from M_GENDER where LANG_CD =99 and RECORD_STATUS<> 'D' and GENDER_CD=:genderId" ,nativeQuery=true)
    public Optional<Gender> findById(Integer genderId);
}
