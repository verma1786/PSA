package com.state.psa.mail.service;

import com.state.psa.mail.domain.UserMailDetails;
import com.state.psa.mail.exception.EntityNotFoundException;
import com.state.psa.mail.repository.UserMailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserMailServiceImpl implements UserMailService{
    @Autowired
    UserMailRepository userMailRepository;

    @Override
    public UserMailDetails getMailId(Long userId) {
        return userMailRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException(UserMailDetails.class, "userId", userId.toString()));
    }

    @Override
    public List<UserMailDetails> getMailIdsByUserId(Long userId) {
        List<UserMailDetails> mailDetailsList= userMailRepository.findByUserIdOrderByIdAsc(userId);
        if(mailDetailsList.isEmpty()){
            throw new EntityNotFoundException(UserMailDetails.class, "userId", userId.toString());
        }
       return mailDetailsList;
    }
}
