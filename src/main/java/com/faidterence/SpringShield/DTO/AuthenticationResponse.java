package com.faidterence.SpringShield.DTO;


import com.faidterence.SpringShield.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AuthenticationResponse {


    private User user;

    public AuthenticationResponse(User user) {
        this.user = user;
    }
}
