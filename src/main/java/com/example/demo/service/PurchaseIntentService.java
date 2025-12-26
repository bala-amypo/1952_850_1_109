// PurchaseIntentService.java (Interface)
package com.example.demo.service;

import com.example.demo.entity.PurchaseIntentRecord;
import java.util.List;

public interface PurchaseIntentService {
    PurchaseIntentRecord createIntent(PurchaseIntentRecord intent);
    PurchaseIntentRecord getIntentById(Long id);
    List<PurchaseIntentRecord> getAllIntents();
    List<PurchaseIntentRecord> getIntentsByUser(Long userId);
}

// ========================================

// PurchaseIntentServiceImpl.java (Implementation)