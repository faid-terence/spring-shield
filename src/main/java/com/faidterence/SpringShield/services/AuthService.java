package com.faidterence.SpringShield.services;


import com.faidterence.SpringShield.DTO.AuthenticationResponse;
import com.faidterence.SpringShield.DTO.LoginResponse;
import com.faidterence.SpringShield.models.User;
import com.faidterence.SpringShield.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {

    private  final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    public ResponseEntity<Object> register(User registerRequest) {
        User user = new User();
        user.setFullNames(registerRequest.getFullNames());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());

        if (user.getFullNames() == null || user.getEmail() == null || user.getPassword() == null) {
            return ResponseEntity.badRequest().body("Please fill all fields");
        }
        if(user.getRole() == null){
            user.setRole("USER");
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        // Save the user
        user = userRepository.save(user);
        return ResponseEntity.ok(new AuthenticationResponse(user).getUser());
    }
    public LoginResponse login(User loginRequest){
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid login credentials"));
        if(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            String token = jwtService.generateToken(user);
           return new LoginResponse(token);
        }
        throw new RuntimeException("Invalid login credentials");
    }
}
