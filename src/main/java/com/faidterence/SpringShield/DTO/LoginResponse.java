package com.faidterence.SpringShield.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {

    private String authenticationToken;

    public LoginResponse(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
