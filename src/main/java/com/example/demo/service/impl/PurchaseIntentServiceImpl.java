package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository intentRepository;

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository intentRepository) {
        this.intentRepository = intentRepository;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        return intentRepository.save(intent);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return intentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intent not found with id: " + id));
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return intentRepository.findAll();
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return intentRepository.findByUserId(userId);
    }
}