package com.state.psa.model.dto;

import com.state.psa.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationResponse implements Serializable {

    private  String jwt;
    private  User user;
    public AuthenticationResponse(String jwt,User user) {
        this.jwt = jwt;
        this.user=user;
    }

}
