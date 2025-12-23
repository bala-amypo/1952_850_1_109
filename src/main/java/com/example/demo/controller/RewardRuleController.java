
package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
public class RewardRuleController {

    private final RewardRuleService rewardRuleService;

    public RewardRuleController(RewardRuleService rewardRuleService) {
        this.rewardRuleService = rewardRuleService;
    }

    @PostMapping
    public ResponseEntity<RewardRule> createRule(@RequestBody RewardRule rule) {
        RewardRule created = rewardRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RewardRule> updateRule(@PathVariable Long id,
                                                 @RequestBody RewardRule rule) {
        RewardRule updated = rewardRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<RewardRule>> getRulesByCard(@PathVariable Long cardId) {
        List<RewardRule> rules = rewardRuleService.getRulesByCard(cardId);
        return ResponseEntity.ok(rules);
    }

    @GetMapping("/active")
    public ResponseEntity<List<RewardRule>> getActiveRules() {
        List<RewardRule> rules = rewardRuleService.getActiveRules();
        return ResponseEntity.ok(rules);
    }

    @GetMapping
    public ResponseEntity<List<RewardRule>> getAllRules() {
        List<RewardRule> rules = rewardRuleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}