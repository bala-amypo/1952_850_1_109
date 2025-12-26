package com.example.demo.service.impl;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CreditCardRecordRepository;
import com.example.demo.service.CreditCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRecordRepository cardRepository;

    public CreditCardServiceImpl(CreditCardRecordRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public CreditCardRecord addCard(CreditCardRecord card) {
        return cardRepository.save(card);
    }

    @Override
    public CreditCardRecord getCardById(Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + id));
    }

    @Override
    public List<CreditCardRecord> getAllCards() {
        return cardRepository.findAll();
    }

    @Override
    public List<CreditCardRecord> getCardsByUser(Long userId) {
        return cardRepository.findByUserId(userId);
    }
}