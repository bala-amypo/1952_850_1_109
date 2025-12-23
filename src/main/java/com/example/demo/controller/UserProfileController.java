package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create user", description = "Creates a new user profile")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid user data")
    })
    public ResponseEntity<UserProfile> createUser(@Valid @RequestBody UserProfile profile) {
        return ResponseEntity.ok(userProfileService.createUser(profile));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieves a user profile by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserProfile> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieves all user profiles")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    public ResponseEntity<List<UserProfile>> getAllUsers() {
        return ResponseEntity.ok(userProfileService.getAllUsers());
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update user status", description = "Updates the active status of a user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Status updated successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserProfile> updateUserStatus(
            @Parameter(description = "User ID") @PathVariable Long id, 
            @Parameter(description = "Active status") @RequestParam boolean active) {
        return ResponseEntity.ok(userProfileService.updateUserStatus(id, active));
    }

    @GetMapping("/lookup/{userId}")
    @Operation(summary = "Lookup user by userId", description = "Finds a user by their unique userId string")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User found"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserProfile> findByUserId(
            @Parameter(description = "User ID string") @PathVariable String userId) {
        return ResponseEntity.ok(userProfileService.findByUserId(userId));
    }
}