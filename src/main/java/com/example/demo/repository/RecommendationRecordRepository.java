package com.example.demo.repository;

import com.example.demo.entity.RecommendationRecord;
import com.example.demo.entity.UserProfile;
import com.example.demo.entity.PurchaseIntentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRecordRepository extends JpaRepository<RecommendationRecord, Long> {

    List<RecommendationRecord> findByUser(UserProfile user);

    List<RecommendationRecord> findByPurchaseIntent(PurchaseIntentRecord purchaseIntent);
}
