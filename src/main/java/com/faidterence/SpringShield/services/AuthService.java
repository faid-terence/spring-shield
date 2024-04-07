package com.faidterence.SpringShield.services;


import com.faidterence.SpringShield.DTO.AuthenticationResponse;
import com.faidterence.SpringShield.models.User;
import com.faidterence.SpringShield.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private  final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    public AuthenticationResponse register(User registerRequest){
        User user = new User();
        user.setFullNames(registerRequest.getFullNames());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(user.getRole());
        user = userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(user, token);
    }
}
