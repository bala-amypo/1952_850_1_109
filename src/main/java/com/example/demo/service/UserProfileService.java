package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllUsers();

    UserProfile getUserById(Long id);

    UserProfile getUserByEmail(String email); // search by email

    UserProfile createUser(UserProfile user);

    UserProfile updateUserStatus(Long id, boolean active);

    void deleteUser(Long id);
}