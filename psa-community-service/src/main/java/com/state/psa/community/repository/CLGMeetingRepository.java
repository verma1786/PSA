package com.state.psa.community.repository;


import com.state.psa.community.model.entity.CLGMeetingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CLGMeetingRepository extends CrudRepository<CLGMeetingDetails, Long> {

}
