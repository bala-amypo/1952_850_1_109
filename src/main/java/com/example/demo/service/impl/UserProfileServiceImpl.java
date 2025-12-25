package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;

public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository repo;
    private final PasswordEncoder encoder;

    public UserProfileServiceImpl(UserProfileRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public UserProfile createUser(UserProfile p) {
        p.setPassword(encoder.encode(p.getPassword()));
        return repo.save(p);
    }

    public UserProfile getUserById(Long id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
    }

    public List<UserProfile> getAllUsers() { return repo.findAll(); }
}