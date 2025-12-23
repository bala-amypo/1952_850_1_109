package com.example.demo.repository;

import com.example.demo.entity.CreditCardRecord;
import com.example.demo.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRecordRepository extends JpaRepository<CreditCardRecord, Long> {

    List<CreditCardRecord> findByUser(UserProfile user);

    @Query("SELECT c FROM CreditCardRecord c WHERE c.user = :user AND c.status = 'ACTIVE'")
    List<CreditCardRecord> findActiveCardsByUser(@Param("user") UserProfile user);
}
