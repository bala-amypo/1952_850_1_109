package com.example.demo.service.impl;

import com.example.demo.dto.JwtResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserProfileService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    
    private final UserProfileService userProfileService;
    private final UserProfileRepository userProfileRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserProfileService userProfileService, 
                          UserProfileRepository userProfileRepository,
                          AuthenticationManager authenticationManager, 
                          JwtUtil jwtUtil) {
        this.userProfileService = userProfileService;
        this.userProfileRepository = userProfileRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse register(RegisterRequest request) {
        UserProfile user = new UserProfile();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        
        UserProfile savedUser = userProfileService.createUser(user);
        String token = jwtUtil.generateToken(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
        
        return new JwtResponse(token, savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
    }

    @Override
    public JwtResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        
        UserProfile user = userProfileRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("User not found"));
        
        if (!user.getActive()) {
            throw new BadRequestException("User account is inactive");
        }
        
        String token = jwtUtil.generateToken(user.getId(), user.getEmail(), user.getRole());
        
        return new JwtResponse(token, user.getId(), user.getEmail(), user.getRole());
    }
}