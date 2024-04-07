package com.faidterence.SpringShield.DTO;


import com.faidterence.SpringShield.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AuthenticationResponse {

    private String authenticationToken;

    private User user;

    public AuthenticationResponse(User user, String authenticationToken) {
        this.user = user;
        this.authenticationToken = authenticationToken;
    }
}
