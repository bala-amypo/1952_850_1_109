package com.example.demo.controller;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-intents")
public class PurchaseIntentController {

    private final PurchaseIntentService purchaseIntentService;

    @Autowired
    public PurchaseIntentController(PurchaseIntentService purchaseIntentService) {
        this.purchaseIntentService = purchaseIntentService;
    }

    @PostMapping
    public PurchaseIntentRecord createPurchaseIntent(
            @RequestBody PurchaseIntentRecord record) {
        return purchaseIntentService.savePurchaseIntent(record);
    }

    
    @GetMapping("/user/{userId}")
    public List<PurchaseIntentRecord> getIntentsByUser(
            @PathVariable Long userId) {
        return purchaseIntentService.getIntentsByUser(userId);
    }

   
    @GetMapping
    public List<PurchaseIntentRecord> getAllPurchaseIntents() {
        return purchaseIntentService.getAllIntents();
    }
}