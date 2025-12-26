package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@Tag(name = "Recommendations", description = "Recommendation management endpoints")
public class RecommendationEngineController {
    
    private final RecommendationEngineService recommendationService;

    public RecommendationEngineController(RecommendationEngineService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate/{intentId}")
    @Operation(summary = "Generate recommendation", description = "Generates a credit card recommendation based on purchase intent")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recommendation generated successfully"),
        @ApiResponse(responseCode = "404", description = "Intent not found")
    })
    public ResponseEntity<RecommendationRecord> generateRecommendation(
            @Parameter(description = "Purchase intent ID") @PathVariable Long intentId) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(intentId));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get recommendations for user", description = "Retrieves all recommendations for a specific user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<RecommendationRecord>> getRecommendationsByUser(
            @Parameter(description = "User ID") @PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUser(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get recommendation by ID", description = "Retrieves a specific recommendation by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Recommendation found"),
        @ApiResponse(responseCode = "404", description = "Recommendation not found")
    })
    public ResponseEntity<RecommendationRecord> getRecommendationById(
            @Parameter(description = "Recommendation ID") @PathVariable Long id) {
        return ResponseEntity.ok(recommendationService.getRecommendationById(id));
    }

    @GetMapping
    @Operation(summary = "Get all recommendations", description = "Retrieves all recommendations in the system")
    @ApiResponse(responseCode = "200", description = "Recommendations retrieved successfully")
    public ResponseEntity<List<RecommendationRecord>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }
}