package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository purchaseIntentRecordRepository;

    @Autowired
    public PurchaseIntentServiceImpl(
            PurchaseIntentRecordRepository purchaseIntentRecordRepository) {
        this.purchaseIntentRecordRepository = purchaseIntentRecordRepository;
    }

    @Override
    public PurchaseIntentRecord savePurchaseIntent(PurchaseIntentRecord record) {
        return purchaseIntentRecordRepository.save(record);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return purchaseIntentRecordRepository.findByUserId(userId);
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return purchaseIntentRecordRepository.findAll();
    }
}
