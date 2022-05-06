package com.state.psa.mail.controller;

import com.state.psa.mail.domain.UserMailDetails;
import com.state.psa.mail.exception.EntityNotFoundException;
import com.state.psa.mail.service.UserMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MailController {
    @Autowired
    private UserMailService userMailService;

    @GetMapping(value = "/{userId}")
    public List<UserMailDetails> getBird(@PathVariable("userId") Long userId) throws EntityNotFoundException {

        return userMailService.getMailIdsByUserId(userId);
       // return userMailService.getMailId(userId);
    }
/*
    @GetMapping(value = "/collection")
    public List<Bird> getBirdValid(@RequestBody BirdCollection birdCollection) throws EntityNotFoundException {
        return birdService.getBirdCollection(birdCollection);
    }

    @GetMapping(value = "/params")
    public Bird getBirdRequestParam(@RequestParam("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @GetMapping(value = "/noexception/{birdId}")
    public Bird getBirdNoException(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBirdNoException(birdId);
    }


    @PostMapping
    public Bird createBird(@RequestBody @Valid Bird bird) {
        return birdService.createBird(bird);
    }

 */
}
