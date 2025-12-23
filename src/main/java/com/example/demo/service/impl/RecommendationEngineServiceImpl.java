package com.example.demo.service.impl;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.repository.RecommendationRecordRepository;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final RecommendationRecordRepository recommendationRecordRepository;

    public RecommendationEngineServiceImpl(RecommendationRecordRepository recommendationRecordRepository) {
        this.recommendationRecordRepository = recommendationRecordRepository;
    }

    @Override
    public List<RecommendationRecord> generateRecommendations(Long userId) {
        // Example logic: Recommend 3 dummy products
        List<RecommendationRecord> recommendations = new ArrayList<>();
        recommendations.add(new RecommendationRecord(userId, "Product A", LocalDateTime.now()));
        recommendations.add(new RecommendationRecord(userId, "Product B", LocalDateTime.now()));
        recommendations.add(new RecommendationRecord(userId, "Product C", LocalDateTime.now()));

        // Save to DB
        recommendationRecordRepository.saveAll(recommendations);

        return recommendations;
    }
}

