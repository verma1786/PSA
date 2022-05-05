package com.state.psa.lost.repository;

import com.state.psa.lost.model.ComplainantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainantRepository extends JpaRepository<ComplainantDetail ,Long> {
}
