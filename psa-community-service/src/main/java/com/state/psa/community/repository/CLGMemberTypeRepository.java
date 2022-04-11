package com.state.psa.community.repository;

import com.state.psa.community.model.entity.CLGMemberType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  CLGMemberTypeRepository extends JpaRepository<CLGMemberType, Long> {
}
