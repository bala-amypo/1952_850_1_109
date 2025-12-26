package com.example.demo.service.impl;

import com.example.demo.entity.RewardRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository ruleRepository;

    public RewardRuleServiceImpl(RewardRuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }

    @Override
    public RewardRule createRule(RewardRule rule) {
        return ruleRepository.save(rule);
    }

    @Override
    public RewardRule getRuleById(Long id) {
        return ruleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rule not found with id: " + id));
    }

    @Override
    public List<RewardRule> getAllRules() {
        return ruleRepository.findAll();
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return ruleRepository.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        return ruleRepository.findByCardId(cardId);
    }
}