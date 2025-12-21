package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseIntentRecord;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.PurchaseIntentRecordRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.PurchaseIntentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseIntentServiceImpl implements PurchaseIntentService {

    private final PurchaseIntentRecordRepository intentRepo;
    private final UserProfileRepository userRepo;

    @Override
    public PurchaseIntentRecord createIntent(PurchaseIntentRecord intent) {
        if (intent.getAmount() <= 0)
            throw new IllegalArgumentException("Amount must be > 0");
        return intentRepo.save(intent);
    }

    @Override
    public List<PurchaseIntentRecord> getIntentsByUser(Long userId) {
        UserProfile user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return intentRepo.findByUser(user);
    }

    @Override
    public PurchaseIntentRecord getIntentById(Long id) {
        return intentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Intent not found"));
    }

    @Override
    public List<PurchaseIntentRecord> getAllIntents() {
        return intentRepo.findAll();
    }
}
