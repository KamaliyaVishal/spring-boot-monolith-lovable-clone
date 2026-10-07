package com.lovable_clone.service;

import com.lovable_clone.dto.auth.AuthResponse;
import com.lovable_clone.dto.auth.LoginRequest;
import com.lovable_clone.dto.auth.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request);

    AuthResponse login(LoginRequest request);
}
