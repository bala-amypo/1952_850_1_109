package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Credit Cards", description = "Credit card management operations")
@SecurityRequirement(name = "Bearer Authentication")
public class CreditCardController {

    private final CreditCardService cardService;

    public CreditCardController(CreditCardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    @Operation(summary = "Add a new credit card", description = "Creates a new credit card record")
    public ResponseEntity<CreditCardRecord> addCard(@Valid @RequestBody CreditCardRecord card) {
        return ResponseEntity.ok(cardService.addCard(card));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get credit card by ID", description = "Retrieves a credit card by its ID")
    public ResponseEntity<CreditCardRecord> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @GetMapping
    @Operation(summary = "Get all credit cards", description = "Retrieves all credit cards")
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(cardService.getAllCards());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get cards by user", description = "Retrieves all credit cards for a specific user")
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(cardService.getCardsByUser(userId));
    }
}
