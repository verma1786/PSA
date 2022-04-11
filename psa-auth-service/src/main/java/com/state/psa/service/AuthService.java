package com.state.psa.service;

import com.state.psa.model.entity.User;

public interface AuthService {
    public User ssoAuthentication(String ssoId, String password);
}
