package com.state.psa.lost.repository;

import com.state.psa.lost.model.LostArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LostArticleRepository extends JpaRepository<LostArticle,Long> {
}
