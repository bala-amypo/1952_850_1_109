package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    public ResponseEntity<CreditCardRecord> addCard(@RequestBody CreditCardRecord card) {
        CreditCardRecord savedCard = creditCardService.addCard(card);
        return ResponseEntity.ok(savedCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCardRecord> updateCard(@PathVariable Long id,
                                                       @RequestBody CreditCardRecord updated) {
        CreditCardRecord savedCard = creditCardService.updateCard(id, updated);
        return ResponseEntity.ok(savedCard);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        List<CreditCardRecord> cards = creditCardService.getCardsByUser(userId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditCardRecord> getCardById(@PathVariable Long id) {
        CreditCardRecord card = creditCardService.getCardById(id);
        return ResponseEntity.ok(card);
    }

    @GetMapping
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        List<CreditCardRecord> cards = creditCardService.getAllCards();
        return ResponseEntity.ok(cards);
    }
}