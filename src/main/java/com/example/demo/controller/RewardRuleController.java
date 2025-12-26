package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
public class RewardRuleController {

    private final RewardRuleService ruleService;

    public RewardRuleController(RewardRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping
    public ResponseEntity<RewardRule> createRule(@RequestBody RewardRule rule) {
        return ResponseEntity.ok(ruleService.createRule(rule));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RewardRule> getRuleById(@PathVariable Long id) {
        return ResponseEntity.ok(ruleService.getRuleById(id));
    }

    @GetMapping
    public ResponseEntity<List<RewardRule>> getAllRules() {
        return ResponseEntity.ok(ruleService.getAllRules());
    }

    @GetMapping("/active")
    public ResponseEntity<List<RewardRule>> getActiveRules() {
        return ResponseEntity.ok(ruleService.getActiveRules());
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<RewardRule>> getRulesByCard(@PathVariable Long cardId) {
        return ResponseEntity.ok(ruleService.getRulesByCard(cardId));
    }
}
