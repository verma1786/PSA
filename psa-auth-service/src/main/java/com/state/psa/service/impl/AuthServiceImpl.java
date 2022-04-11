package com.state.psa.service.impl;

import com.state.psa.model.entity.User;
import com.state.psa.service.AuthService;
import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public User ssoAuthentication(String ssoId, String password) {
        String validUser=  restTemplate.getForObject("",String.class);
        return null;
    }
}
