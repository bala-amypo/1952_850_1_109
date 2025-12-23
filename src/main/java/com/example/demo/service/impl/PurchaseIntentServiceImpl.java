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
        if (intent.getAmount() == null || intent.getAmount() <= 0) {
            throw new BadRequestException("Amount must be > 0");
        }
        
        if (intent.getUser() == null && intent.getUserId() != null) {
            UserProfile user = userRepository.findById(intent.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
            intent.setUser(user);
        }
        
        if (intent.getUser() == null) {
            throw new BadRequestException("User is required");
        }
        
        return purchaseIntentRepository.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        return purchaseIntentRepository.findByUser_Id(userId);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return purchaseIntentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Intent not found"));
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return purchaseIntentRepository.findAll();
    }
}