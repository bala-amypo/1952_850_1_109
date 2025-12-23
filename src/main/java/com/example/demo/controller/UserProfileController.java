package com.example.demo.controller;

import com.example.demo.entity.UserProfile;
import com.example.demo.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public UserProfile getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public UserProfile getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email);
    }

    @GetMapping
    public List<UserProfile> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserProfile createUser(@RequestBody UserProfile user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}/status")
    public UserProfile updateUserStatus(@PathVariable Long id, @RequestParam boolean active) {
        return service.updateUserStatus(id, active);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}