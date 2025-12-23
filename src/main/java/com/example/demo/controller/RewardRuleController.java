package com.example.demo.controller;

import com.example.demo.entity.RewardRule;
import com.example.demo.service.RewardRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reward-rules")
@Tag(name = "Reward Rules", description = "Reward rule management endpoints")
public class RewardRuleController {
    
    private final RewardRuleService rewardRuleService;

    public RewardRuleController(RewardRuleService rewardRuleService) {
        this.rewardRuleService = rewardRuleService;
    }

    @PostMapping
    @Operation(summary = "Create reward rule", description = "Creates a new reward rule")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rule created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid rule data")
    })
    public ResponseEntity<RewardRule> createRule(@Valid @RequestBody RewardRule rule) {
        return ResponseEntity.ok(rewardRuleService.createRule(rule));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update reward rule", description = "Updates an existing reward rule")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rule updated successfully"),
        @ApiResponse(responseCode = "404", description = "Rule not found")
    })
    public ResponseEntity<RewardRule> updateRule(
            @Parameter(description = "Rule ID") @PathVariable Long id, 
            @Valid @RequestBody RewardRule rule) {
        return ResponseEntity.ok(rewardRuleService.updateRule(id, rule));
    }

    @GetMapping("/card/{cardId}")
    @Operation(summary = "Get rules for card", description = "Retrieves all reward rules for a specific card")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Rules retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Card not found")
    })
    public ResponseEntity<List<RewardRule>> getRulesByCard(
            @Parameter(description = "Card ID") @PathVariable Long cardId) {
        return ResponseEntity.ok(rewardRuleService.getRulesByCard(cardId));
    }

    @GetMapping("/active")
    @Operation(summary = "Get active rules", description = "Retrieves all currently active reward rules")
    @ApiResponse(responseCode = "200", description = "Active rules retrieved successfully")
    public ResponseEntity<List<RewardRule>> getActiveRules() {
        return ResponseEntity.ok(rewardRuleService.getActiveRules());
    }

    @GetMapping
    @Operation(summary = "Get all rules", description = "Retrieves all reward rules in the system")
    @ApiResponse(responseCode = "200", description = "Rules retrieved successfully")
    public ResponseEntity<List<RewardRule>> getAllRules() {
        return ResponseEntity.ok(rewardRuleService.getAllRules());
    }
}