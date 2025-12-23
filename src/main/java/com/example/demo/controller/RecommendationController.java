package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationEngineController {

    private final RecommendationEngineService recommendationEngineService;

    public RecommendationEngineController(RecommendationEngineService recommendationEngineService) {
        this.recommendationEngineService = recommendationEngineService;
    }

    @GetMapping("/{userId}")
    public List<RecommendationRecord> getRecommendations(@PathVariable Long userId) {
        return recommendationEngineService.generateRecommendations(userId);
    }
}