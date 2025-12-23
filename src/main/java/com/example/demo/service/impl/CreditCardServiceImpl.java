package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.entity.UserProfile;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.repository.UserProfileRepository;
import com.example.demo.service.CreditCardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository cardRepo;
    private final UserProfileRepository userRepo;

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        card.setId(null);
        card.setCreatedAt(null);
        if (card.getAnnualFee() == null) card.setAnnualFee(0.0);
        if (card.getStatus() == null) card.setStatus("ACTIVE");
        
        if (card.getUser() == null && card.getUserId() != null && card.getUserId() > 0) {
            UserProfile user = userRepo.findById(card.getUserId()).orElse(null);
            if (user != null) {
                card.setUser(user);
            }
        }
        
        return cardRepo.save(card);
    }

    @Override
    public CreditCardRecord updateCard(Long id, CreditCardRecord updated) {
        CreditCardRecord existing = getCardById(id);
        existing.setCardName(updated.getCardName());
        existing.setIssuer(updated.getIssuer());
        existing.setCardType(updated.getCardType());
        existing.setAnnualFee(updated.getAnnualFee());
        existing.setStatus(updated.getStatus());
        return cardRepo.save(existing);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        if (userId == null || userId <= 0) {
            return new ArrayList<>();
        }
        UserProfile user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            return new ArrayList<>();
        }
        return cardRepo.findByUser(user);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        if (id == null || id <= 0) {
            return new CreditCardRecord();
        }
        return cardRepo.findById(id).orElse(new CreditCardRecord());
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return cardRepo.findAll();
    }
}
