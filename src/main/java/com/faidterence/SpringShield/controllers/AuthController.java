package com.faidterence.SpringShield.controllers;


import com.faidterence.SpringShield.DTO.AuthenticationResponse;
import com.faidterence.SpringShield.DTO.LoginResponse;
import com.faidterence.SpringShield.models.User;
import com.faidterence.SpringShield.services.AuthService;
import com.faidterence.SpringShield.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final AuthService authService;


    @PostMapping("/api/v1/auth/register")
    public ResponseEntity<?> registerUser(@RequestBody User registerRequest) {
        if (userService.loadUserByEmail(registerRequest.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        AuthenticationResponse registeredUser = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/api/v1/auth/login")
    public ResponseEntity<LoginResponse> login (@RequestBody User loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
