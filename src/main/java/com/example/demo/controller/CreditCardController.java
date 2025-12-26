package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    private final CreditCardService cardService;

    public CreditCardController(CreditCardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public ResponseEntity<CreditCardRecord> addCard(@RequestBody CreditCardRecord card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardRecord> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cardService.getCardsByUser(userId));
    }
}

// ========================================

// PurchaseIntentController.java
package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intents")
public class PurchaseIntentController {

    private final PurchaseIntentService intentService;

    public PurchaseIntentController(PurchaseIntentService intentService) {
        this.intentService = intentService;
    }

    @PostMapping
    public ResponseEntity<PurchaseIntentRecord> createIntent(@RequestBody PurchaseIntentRecord intent) {
        return ResponseEntity.ok(intentService.createIntent(intent));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseIntentRecord> getIntentById(@PathVariable Long id) {
        return ResponseEntity.ok(intentService.getIntentById(id));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseIntentRecord>> getAllIntents() {
        return ResponseEntity.ok(intentService.getAllIntents());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseIntentRecord>> getIntentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(intentService.getIntentsByUser(userId));
    }
}

// ========================================

// RecommendationController.java
package com.example.demo.controller;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.service.RecommendationEngineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationEngineService recommendationService;

    public RecommendationController(RecommendationEngineService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/generate/{intentId}")
    public ResponseEntity<RecommendationRecord> generateRecommendation(@PathVariable Long intentId) {
        return ResponseEntity.ok(recommendationService.generateRecommendation(intentId));
    }

    @GetMapping
    public ResponseEntity<List<RecommendationRecord>> getAllRecommendations() {
        return ResponseEntity.ok(recommendationService.getAllRecommendations());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationRecord>> getRecommendationsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(recommendationService.getRecommendationsByUser(userId));
    }
}