package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations", description = "Recommendation management endpoints")
public class RecommendationController {
    
    private final RecommendationEngineService recommendationService;

    public RecommendationController(RecommendationEngineService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate/{intentId}")
    @Operation(summary = "Generate recommendation")
    public ResponseEntity<RecommendationRecord> generateRecommendation(@PathVariable Long intentId) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(intentId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get recommendations for user")
    public ResponseEntity<List<RecommendationRecord>> getRecommendationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUser(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recommendation by ID")
    public ResponseEntity<RecommendationRecord> getRecommendationById(@PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.getRecommendationById(id));
    }

    @GetMapping
    @Operation(summary = "Get all recommendations")
    public ResponseEntity<List<RecommendationRecord>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }
}