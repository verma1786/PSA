package com.state.psa.mail.repository;

import com.state.psa.mail.domain.UserMailDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserMailRepository extends JpaRepository<UserMailDetails,Long> {
    public List<UserMailDetails> findByUserIdOrderByIdAsc(Long userId);
}
