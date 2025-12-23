package com.example.demo.service;

import com.example.demo.entity.RecommendationRecord;
import java.util.List;

public interface RecommendationEngineService {
    List<RecommendationRecord> generateRecommendations(Long userId);
}

