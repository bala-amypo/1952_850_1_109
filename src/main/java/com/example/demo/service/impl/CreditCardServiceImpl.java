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
        if (card.getAnnualFee() < 0)
            throw new IllegalArgumentException("Annual fee must be >= 0");
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
        UserProfile user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return cardRepo.findByUser(user);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Card not found"));
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return cardRepo.findAll();
    }
}
