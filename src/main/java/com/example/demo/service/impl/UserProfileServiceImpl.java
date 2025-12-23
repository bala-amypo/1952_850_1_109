package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userRepo;

    @Override
    public UserProfile createUser(UserProfile profile) {
        profile.setId(null);
        profile.setCreatedAt(null);
        return userRepo.save(profile);
    }

    @Override
    public UserProfile getUserById(Long id) {
        if (id == null || id <= 0) {
            return new UserProfile();
        }
        return userRepo.findById(id).orElse(new UserProfile());
    }

    @Override
    public UserProfile findByUserId(String userId) {
        return userRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with userId: " + userId));
    }

    @Override
    public UserProfile findByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserProfile updateUserStatus(Long id, boolean active) {
        UserProfile user = getUserById(id);
        user.setActive(active);
        return userRepo.save(user);
    }
}
