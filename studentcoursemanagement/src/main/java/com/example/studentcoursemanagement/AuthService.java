package com.example.studentcoursemanagement;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    AuthResponse register(RegisterRequest request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new UsernameNotFoundException("Username already exists");
        }
        User user = User.builder().username(request.getUsername()).password(passwordEncoder.encode(request.getPassword())).role(request.getRole() != null ? request.getRole() : Role.ROLE_USER).build();
        userRepository.save(user);
        String token = jwtService.generateToken(user.getUsername(),user.getRole());
        return AuthResponse.builder().username(user.getUsername()).token(token).role(user.getRole()).build();
    }
    AuthResponse login(LoginRequest loginRequest){
        if(!userRepository.existsByUsername(loginRequest.getUsername())){
            throw new UsernameNotFoundException("no User found with username");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(loginRequest.getUsername(),user.getRole());
        return AuthResponse.builder().username(user.getUsername()).token(token).role(user.getRole()).build();
    }
}
