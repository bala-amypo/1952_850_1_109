package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.entity.UserProfile;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.PurchaseIntentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PurchaseIntentServiceImpl implements PurchaseIntentService {
    
    private final PurchaseIntentRecordRepository purchaseIntentRepository;
    private final UserProfileRepository userRepository;

    public PurchaseIntentServiceImpl(PurchaseIntentRecordRepository purchaseIntentRepository, UserProfileRepository userRepository) {
        this.purchaseIntentRepository = purchaseIntentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        intent.setId(null);
        intent.setIntentDate(null);
        if (intent.getAmount() == null) intent.setAmount(1.0);
        
        if (intent.getUser() == null && intent.getUserId() != null && intent.getUserId() > 0) {
            UserProfile user = userRepository.findById(intent.getUserId()).orElse(null);
            if (user != null) {
                intent.setUser(user);
            }
        }
        
        return purchaseIntentRepository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        if (userId == null || userId <= 0) {
            return new ArrayList<>();
        }
        return purchaseIntentRepository.findByUser_Id(userId);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        if (id == null || id <= 0) {
            return new PurchaseIntentRecord();
        }
        return purchaseIntentRepository.findById(id).orElse(new PurchaseIntentRecord());
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return purchaseIntentRepository.findAll();
    }
}