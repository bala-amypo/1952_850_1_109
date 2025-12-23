



package com.example.demo.service;

import com.example.demo.entity.RecommendationRecord;
import java.util.List;

public interface RecommendationEngineService {
    List<RecommendationRecord> generateRecommendations(Long userId);
}





package com.example.demo.service;

import com.example.demo.entity.RewardRule;

import java.util.List;

public interface RewardRuleService {
    RewardRule createRule(RewardRule rule);
    RewardRule updateRule(Long id, RewardRule updated);
    List<RewardRule> getRulesByCard(Long cardId);
    List<RewardRule> getActiveRules();
    List<RewardRule> getAllRules();
}





package com.example.demo.service;

import com.example.demo.entity.UserProfile;
import java.util.List;

public interface UserProfileService {

    List<UserProfile> getAllUsers();

    UserProfile getUserById(Long id);

    UserProfile getUserByEmail(String email); // search by email

    UserProfile createUser(UserProfile user);

    UserProfile updateUserStatus(Long id, boolean active);

    void deleteUser(Long id);
}