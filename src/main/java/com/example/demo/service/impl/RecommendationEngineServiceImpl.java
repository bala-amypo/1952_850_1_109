package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.service.RecommendationEngineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        PurchaseIntentRecord intent = intentRepo.findById(intentId)
                .orElseThrow(() -> new EntityNotFoundException("Intent not found"));
        UserProfile user = intent.getUser();
        List<CreditCardRecord> cards = cardRepo.findActiveCardsByUser(user);

        double maxReward = 0.0;
        CreditCardRecord bestCard = null;
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

        if (bestCard == null)
            throw new EntityNotFoundException("No valid card found for this intent");

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
        return recRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Recommendation not found"));
    }

    @Override
    public List<RecommendationRecord> getRecommendationsByUser(Long userId) {
        UserProfile user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return recRepo.findByUser(user);
    }

    @Override
    public List<RecommendationRecord> getAllRecommendations() {
        return recRepo.findAll();
    }
}
