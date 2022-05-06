package com.state.psa.lost.controller;

import com.state.psa.lost.common.exception.ResourceNotFoundException;
import com.state.psa.lost.model.LostArticle;
import com.state.psa.lost.service.ILostArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * get all lost article details
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/psa-lost")
public class LostPropController {

    @Autowired
    private ILostArticleService lostArticleService;



    @GetMapping("/articles")
    public List<LostArticle> getAllLostArticles() {
        return lostArticleService.findAll();
    }

    @PostMapping("/articles")
    public LostArticle createLostArticle(@RequestBody LostArticle lostArticle) {
        return lostArticleService.save(lostArticle);
    }

    @GetMapping("/articles/{id}")
    public LostArticle getLostArticleById(@PathVariable(value = "id") Long lostId) {
        return lostArticleService.findById(lostId).orElseThrow(() -> new ResourceNotFoundException("LostArticle", "id", lostId));
    }

    @PutMapping("/articles/{id}")
    public LostArticle updateLostArticle(@PathVariable(value = "id") Long lostId, @RequestBody LostArticle lostArticle) {
        LostArticle updatedLostArticle = lostArticleService.updateLostArticle(lostId);
        return updatedLostArticle;
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<?> deleteLostArticle(@PathVariable(value = "id") Long lostId) {
        lostArticleService.deleteLostArticle(lostId);
        return ResponseEntity.ok().build();
    }

}
