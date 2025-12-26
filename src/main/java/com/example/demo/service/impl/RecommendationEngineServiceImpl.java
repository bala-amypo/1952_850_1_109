// RecommendationEngineService.java (Interface)
package com.example.demo.service;

import com.example.demo.entity.RecommendationRecord;
import java.util.List;

public interface RecommendationEngineService {
    RecommendationRecord generateRecommendation(Long intentId);
    List<RecommendationRecord> getAllRecommendations();
    List<RecommendationRecord> getRecommendationsByUser(Long userId);
}

// ========================================

// RecommendationEngineServiceImpl.java (Implementation)
package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepository;
    private final UserProfileRepository userRepository;
    private final CreditCardRecordRepository cardRepository;
    private final RewardRuleRepository ruleRepository;
    private final RecommendationRecordRepository recommendationRepository;

    public RecommendationEngineServiceImpl(
            PurchaseIntentRecordRepository intentRepository,
            UserProfileRepository userRepository,
            CreditCardRecordRepository cardRepository,
            RewardRuleRepository ruleRepository,
            RecommendationRecordRepository recommendationRepository) {
        this.intentRepository = intentRepository;
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.ruleRepository = ruleRepository;
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public RecommendationRecord generateRecommendation(Long intentId) {
        // Get purchase intent
        PurchaseIntentRecord intent = intentRepository.findById(intentId)
                .orElseThrow(() -> new ResourceNotFoundException("Intent not found"));

        // Verify user exists and is active
        UserProfile user = userRepository.findById(intent.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (user.getActive() != null && !user.getActive()) {
            throw new BadRequestException("User account is not active");
        }

        // Get user's active cards
        List<CreditCardRecord> activeCards = cardRepository.findActiveCardsByUser(intent.getUserId());
        if (activeCards.isEmpty()) {
            throw new BadRequestException("No active cards found for user");
        }

        // Find best card based on reward rules
        CreditCardRecord bestCard = null;
        double maxReward = 0.0;
        RewardRule bestRule = null;

        for (CreditCardRecord card : activeCards) {
            List<RewardRule> rules = ruleRepository.findActiveRulesForCardCategory(
                    card.getId(), intent.getCategory());
            
            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                if (reward > maxReward) {
                    maxReward = reward;
                    bestCard = card;
                    bestRule = rule;
                }
            }
        }

        if (bestCard == null) {
            throw new BadRequestException("No matching reward rules found");
        }

        // Create recommendation
        RecommendationRecord recommendation = new RecommendationRecord();
        recommendation.setUserId(intent.getUserId());
        recommendation.setPurchaseIntentId(intentId);
        recommendation.setRecommendedCardId(bestCard.getId());
        recommendation.setExpectedRewardValue(maxReward);
        
        String details = String.format(
                "{\"cardId\":%d,\"ruleId\":%d,\"multiplier\":%.2f,\"amount\":%.2f}",
                bestCard.getId(), bestRule.getId(), bestRule.getMultiplier(), intent.getAmount()
        );
        recommendation.setCalculationDetailsJson(details);

        return recommendationRepository.save(recommendation);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recommendationRepository.findAll();
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        return recommendationRepository.findByUserId(userId);
    }
}