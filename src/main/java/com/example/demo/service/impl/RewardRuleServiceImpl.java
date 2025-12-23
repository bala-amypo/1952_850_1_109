package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.entity.RewardRule;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.repository.RewardRuleRepository;
import com.example.demo.service.RewardRuleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RewardRuleServiceImpl implements RewardRuleService {

    private final RewardRuleRepository rewardRepo;
    private final CreditCardRecordRepository cardRepo;

    @Override
    public RewardRule createRule(RewardRule rule) {
        rule.setId(null);
        if (rule.getMultiplier() == null) rule.setMultiplier(1.0);
        if (rule.getActive() == null) rule.setActive(true);
        
        if (rule.getCard() == null && rule.getCardId() != null && rule.getCardId() > 0) {
            CreditCardRecord card = cardRepo.findById(rule.getCardId()).orElse(null);
            if (card != null) {
                rule.setCard(card);
            }
        }
        
        return rewardRepo.save(rule);
    }

    @Override
    public RewardRule updateRule(Long id, RewardRule updated) {
        RewardRule existing = rewardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rule not found"));
        existing.setCategory(updated.getCategory());
        existing.setRewardType(updated.getRewardType());
        existing.setMultiplier(updated.getMultiplier());
        existing.setActive(updated.getActive());
        return rewardRepo.save(existing);
    }

    @Override
    public List<RewardRule> getRulesByCard(Long cardId) {
        if (cardId == null || cardId <= 0) {
            return new ArrayList<>();
        }
        CreditCardRecord card = cardRepo.findById(cardId).orElse(null);
        if (card == null) {
            return new ArrayList<>();
        }
        return rewardRepo.findByCard(card);
    }

    @Override
    public List<RewardRule> getActiveRules() {
        return rewardRepo.findByActiveTrue();
    }

    @Override
    public List<RewardRule> getAllRules() {
        return rewardRepo.findAll();
    }
}
