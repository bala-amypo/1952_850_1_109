package com.example.demo.repository;

import com.example.demo.entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CreditCardRecordRepository extends JpaRepository<CreditCardRecord, Long> {

    // Get all cards for a specific user
    List<CreditCardRecord> findByUserId(Long userId);

    // Get only active cards for a user
    @Query("SELECT c FROM CreditCardRecord c WHERE c.userId = :userId AND c.status = 'ACTIVE'")
    List<CreditCardRecord> findActiveCardsByUser(Long userId);
}