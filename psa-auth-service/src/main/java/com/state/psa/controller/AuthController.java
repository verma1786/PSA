package com.state.psa.controller;

import com.state.psa.model.dto.AuthenticationRequest;
import com.state.psa.model.dto.AuthenticationResponse;
import com.state.psa.model.entity.User;
import com.state.psa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticationFromSSOId(@RequestBody AuthenticationRequest authenticationRequest){
        User user= authService.ssoAuthentication(authenticationRequest.getSsoId(),authenticationRequest.getPassword());
        String jwt="null";
     return ResponseEntity.ok(new AuthenticationResponse(jwt,user));
    }
}
