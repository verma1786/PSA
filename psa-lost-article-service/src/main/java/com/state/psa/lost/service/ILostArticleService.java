package com.state.psa.lost.service;

import com.state.psa.lost.model.LostArticle;

import java.util.List;
import java.util.Optional;

public interface ILostArticleService {
    public List<LostArticle> findAll();

    public LostArticle save(LostArticle lostArticle);

    public Optional<LostArticle> findById(Long lostId);

    public LostArticle updateLostArticle(Long lostId);

    public void deleteLostArticle(Long lostId);
}
