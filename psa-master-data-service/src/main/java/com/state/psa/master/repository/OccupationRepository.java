package com.state.psa.master.repository;

import com.state.psa.master.model.entity.Occupation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OccupationRepository extends JpaRepository<Occupation, Integer> {
    Optional<Occupation> findByOccupationIdAndLangId(Integer occupationId, Integer langId);
}
