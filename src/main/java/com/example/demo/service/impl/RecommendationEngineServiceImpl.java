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





package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository rewardRuleRepository;

    public RewardRuleServiceImpl(RewardRuleRepository rewardRuleRepository) {
        this.rewardRuleRepository = rewardRuleRepository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        if (rule.getMultiplier() == null || rule.getMultiplier() <= 0) {
            throw new BadRequestException("Price multiplier must be > 0");
        }
        return rewardRuleRepository.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule updated) {
        RewardRule existing = rewardRuleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("RewardRule not found with id " + id));

        if (updated.getMultiplier() == null || updated.getMultiplier() <= 0) {
            throw new BadRequestException("Price multiplier must be > 0");
        }

        existing.setCategory(updated.getCategory());
        existing.setCardId(updated.getCardId());
        existing.setMultiplier(updated.getMultiplier());
        existing.setRewardType(updated.getRewardType());
        existing.setActive(updated.getActive());

        return rewardRuleRepository.save(existing);
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return rewardRuleRepository.findByCardId(cardId);
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return rewardRuleRepository.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return rewardRuleRepository.findAll();
    }
}




package com.example.demo.service.impl;

import com.example.demo.entity.UserProfile;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.UserProfileService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileServiceImpl(UserProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserProfile> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserProfile getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public UserProfile getUserByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }

    @Override
    public UserProfile createUser(UserProfile user) {
        return repository.save(user);
    }

    @Override
    public UserProfile updateUserStatus(Long id, boolean active) {
        UserProfile user = getUserById(id);
        user.setActive(active);
        return repository.save(user); // return updated user
    }

    @Override
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}
