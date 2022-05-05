package com.state.psa.lost.service.impl;

import com.state.psa.lost.exception.ResourceNotFoundException;
import com.state.psa.lost.model.LostArticle;
import com.state.psa.lost.repository.LostArticleRepository;
import com.state.psa.lost.service.ILostArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LostArticleServiceImpl implements ILostArticleService {

    @Autowired
    private LostArticleRepository lostArticleRepository;

    @Override
    public List<LostArticle> findAll() {
        return lostArticleRepository.findAll();
    }

    @Override
    public LostArticle save(LostArticle lostArticle) {
        return lostArticleRepository.save(lostArticle);
    }

    @Override
    public Optional<LostArticle> findById(Long lostId) {
        return lostArticleRepository.findById(lostId);
    }

    @Override
    public LostArticle updateLostArticle(Long lostId) {
        LostArticle lostArticle = lostArticleRepository.findById(lostId).orElseThrow(() -> new ResourceNotFoundException("LostArticle", "id", lostId));

        //lostArticle.setPostName(commentDetails.getPostName());
        //lostArticle.setComment(commentDetails.getComment());

        LostArticle updatedComment = lostArticleRepository.save(lostArticle);
        return updatedComment;
    }

    @Override
    public void deleteLostArticle(Long lostId) {
        LostArticle comment = lostArticleRepository.findById(lostId).orElseThrow(() -> new ResourceNotFoundException("LostArticle", "id", lostId));
        lostArticleRepository.delete(comment);
    }
}
