package com.example.demo.controller;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
@Tag(name = "Credit Cards", description = "Credit card management endpoints")
public class CreditCardController {
    
    private final CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping
    @Operation(summary = "Add credit card", description = "Creates a new credit card record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Card created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid card data")
    })
    public ResponseEntity<CreditCardRecord> addCard(@Valid @RequestBody CreditCardRecord card) {
        return ResponseEntity.ok(creditCardService.addCard(card));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update credit card", description = "Updates an existing credit card record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Card updated successfully"),
        @ApiResponse(responseCode = "404", description = "Card not found")
    })
    public ResponseEntity<CreditCardRecord> updateCard(
            @Parameter(description = "Card ID") @PathVariable Long id, 
            @Valid @RequestBody CreditCardRecord card) {
        return ResponseEntity.ok(creditCardService.updateCard(id, card));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get cards for user", description = "Retrieves all credit cards for a specific user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cards retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<CreditCardRecord>> getCardsByUser(
            @Parameter(description = "User ID") @PathVariable Long userId) {
        return ResponseEntity.ok(creditCardService.getCardsByUser(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get card by ID", description = "Retrieves a specific credit card by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Card found"),
        @ApiResponse(responseCode = "404", description = "Card not found")
    })
    public ResponseEntity<CreditCardRecord> getCardById(
            @Parameter(description = "Card ID") @PathVariable Long id) {
        return ResponseEntity.ok(creditCardService.getCardById(id));
    }

    @GetMapping
    @Operation(summary = "Get all cards", description = "Retrieves all credit cards in the system")
    @ApiResponse(responseCode = "200", description = "Cards retrieved successfully")
    public ResponseEntity<List<CreditCardRecord>> getAllCards() {
        return ResponseEntity.ok(creditCardService.getAllCards());
    }
}