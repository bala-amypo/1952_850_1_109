package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendationEngineServiceImpl implements RecommendationEngineService {

    private final PurchaseIntentRecordRepository intentRepo;
    private final CreditCardRecordRepository cardRepo;
    private final RewardRuleRepository ruleRepo;
    private final RecommendationRecordRepository recRepo;
    private final UserProfileRepository userRepo;

    @Override
    public RecommendationRecord generateRecommendation(Long intentId) {
        if (intentId == null || intentId <= 0) {
            throw new IllegalArgumentException("Invalid intent ID");
        }
        
        PurchaseIntentRecord intent = intentRepo.findById(intentId).orElse(null);
        if (intent == null || intent.getUser() == null) {
            throw new EntityNotFoundException("Intent or user not found");
        }
        
        UserProfile user = intent.getUser();
        List<CreditCardRecord> cards = cardRepo.findActiveCardsByUser(user);
        
        if (cards.isEmpty()) {
            throw new EntityNotFoundException("No active cards found for user");
        }

        double maxReward = 0.0;
        CreditCardRecord bestCard = cards.get(0); // Default to first card
        StringBuilder calcDetails = new StringBuilder("{");

        for (CreditCardRecord card : cards) {
            List<RewardRule> rules = ruleRepo.findActiveRulesForCardCategory(card, intent.getCategory());
            for (RewardRule rule : rules) {
                double reward = intent.getAmount() * rule.getMultiplier();
                calcDetails.append("\"").append(card.getCardName())
                           .append("\":").append(reward).append(",");
                if (reward > maxReward) {
                    maxReward = reward;
                    bestCard = card;
                }
            }
        }

        calcDetails.append("}");

        RecommendationRecord recommendation = new RecommendationRecord();
        recommendation.setUser(user);
        recommendation.setPurchaseIntent(intent);
        recommendation.setRecommendedCard(bestCard);
        recommendation.setExpectedRewardValue(maxReward);
        recommendation.setCalculationDetailsJson(calcDetails.toString());

        return recRepo.save(recommendation);
    }

    @Override
    public RecommendationRecord getRecommendationById(Long id) {
        if (id == null || id <= 0) {
            return new RecommendationRecord();
        }
        return recRepo.findById(id).orElse(new RecommendationRecord());
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        if (userId == null || userId <= 0) {
            return new ArrayList<>();
        }
        UserProfile user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        return recRepo.findByUser(user);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recRepo.findAll();
    }
}
