package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
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
@RequestMapping("/api/intents")
@Tag(name = "Purchase Intents", description = "Purchase intent management endpoints")
public class PurchaseIntentController {
    
    private final PurchaseIntentService purchaseIntentService;

    public PurchaseIntentController(PurchaseIntentService purchaseIntentService) {
        this.purchaseIntentService = purchaseIntentService;
    }

    @PostMapping
    @Operation(summary = "Submit purchase intent", description = "Creates a new purchase intent record")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Intent created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid intent data")
    })
    public ResponseEntity<PurchaseIntentRecord> createIntent(@Valid @RequestBody PurchaseIntentRecord intent) {
        return ResponseEntity.ok(purchaseIntentService.createIntent(intent));
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get intents for user", description = "Retrieves all purchase intents for a specific user")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Intents retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<PurchaseIntentRecord>> getIntentsByUser(
            @Parameter(description = "User ID") @PathVariable Long userId) {
        return ResponseEntity.ok(purchaseIntentService.getIntentsByUser(userId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get intent by ID", description = "Retrieves a specific purchase intent by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Intent found"),
        @ApiResponse(responseCode = "404", description = "Intent not found")
    })
    public ResponseEntity<PurchaseIntentRecord> getIntentById(
            @Parameter(description = "Intent ID") @PathVariable Long id) {
        return ResponseEntity.ok(purchaseIntentService.getIntentById(id));
    }

    @GetMapping
    @Operation(summary = "Get all intents", description = "Retrieves all purchase intents in the system")
    @ApiResponse(responseCode = "200", description = "Intents retrieved successfully")
    public ResponseEntity<List<PurchaseIntentRecord>> getAllIntents() {
        return ResponseEntity.ok(purchaseIntentService.getAllIntents());
    }
}