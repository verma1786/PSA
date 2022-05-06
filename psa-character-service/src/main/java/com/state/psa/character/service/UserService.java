package com.state.psa.character.service;

import com.state.psa.character.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

//    @Autowired
//    private UserRepository userRepository;

    public User createUser(User user) {
        return user;//userRepository.save(user);
    }

}