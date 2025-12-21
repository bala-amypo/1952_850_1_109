package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "User management endpoints")
public class UserProfileController {
    
    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<UserProfile> createUser(@Valid @RequestBody UserProfile profile) {
        return ResponseEntity.ok(userProfileService.createUser(profile));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<UserProfile> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update user status")
    public ResponseEntity<UserProfile> updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        return ResponseEntity.ok(userProfileService.updateUserStatus(id, active));
    }

    @GetMapping("/lookup/{userId}")
    @Operation(summary = "Lookup user by userId")
    public ResponseEntity<UserProfile> findByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userProfileService.findByUserId(userId));
    }
}