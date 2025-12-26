package com.example.demo.service;

import com.example.demo.entity.RewardRule;
import java.util.List;

public interface RewardRuleService {
    RewardRule createRule(RewardRule rule);
    RewardRule getRuleById(Long id);
    List<RewardRule> getAllRules();
    List<RewardRule> getActiveRules();
    List<RewardRule> getRulesByCard(Long cardId);
}
