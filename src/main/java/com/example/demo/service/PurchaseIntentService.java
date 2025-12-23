package com.example.demo.service;

import com.example.demo.entity.PurchaseIntentRecord;
import java.util.List;

public interface PurchaseIntentService {

    PurchaseIntentRecord savePurchaseIntent(PurchaseIntentRecord record);

    List<PurchaseIntentRecord> getIntentsByUser(Long userId);

    List<PurchaseIntentRecord> getAllIntents();
}
