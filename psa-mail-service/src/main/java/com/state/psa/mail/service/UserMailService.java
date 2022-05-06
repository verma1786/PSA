package com.state.psa.mail.service;

import com.state.psa.mail.domain.UserMailDetails;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserMailService {
    public UserMailDetails getMailId(Long userId);
    public List<UserMailDetails> getMailIdsByUserId(Long userId);
}
