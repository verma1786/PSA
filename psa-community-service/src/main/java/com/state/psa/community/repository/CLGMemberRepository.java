package com.state.psa.community.repository;


import com.state.psa.community.model.entity.CLGMemberDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CLGMemberRepository extends JpaRepository<CLGMemberDetails, Long> {
}
