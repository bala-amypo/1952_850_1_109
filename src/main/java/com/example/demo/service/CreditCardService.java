package com.example.demo.service;

import com.example.demo.entity.CreditCardRecord;
import java.util.List;

public interface CreditCardService {
    CreditCardRecord addCard(CreditCardRecord card);
    CreditCardRecord getCardById(Long id);
    List<CreditCardRecord> getAllCards();
    List<CreditCardRecord> getCardsByUser(Long userId);
}
