package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository repository;

    public CreditCardServiceImpl(CreditCardRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        if (card.getAnnualFee() < 0) {
            throw new IllegalArgumentException("Annual fee must be >= 0");
        }
        return repository.save(card);
    }

    @Override
    public CreditCardRecord updateCard(Long id, CreditCardRecord updated) {
        CreditCardRecord card = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));

        card.setCardName(updated.getCardName());
        card.setIssuer(updated.getIssuer());
        card.setCardType(updated.getCardType());
        card.setAnnualFee(updated.getAnnualFee());
        card.setStatus(updated.getStatus());

        return repository.save(card);
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return repository.findAll();
    }
}
