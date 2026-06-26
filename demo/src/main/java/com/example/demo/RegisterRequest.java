package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// DTOs
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}

@Data @NoArgsConstructor @AllArgsConstructor @Builder
class LoginRequest {
    private String username;
    private String password;
}

@Data @NoArgsConstructor @AllArgsConstructor @Builder
class AuthResponse {
    private String token;
    private String username;
    private Role role;
}
