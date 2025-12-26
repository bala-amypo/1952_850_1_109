// UserProfileRepository.java
package com.example.demo.repository;

import com.example.demo.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
}

// ========================================

// CreditCardRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CreditCardRecordRepository extends JpaRepository<CreditCardRecord, Long> {
    List<CreditCardRecord> findByUserId(Long userId);
    
    @Query("SELECT c FROM CreditCardRecord c WHERE c.userId = :userId AND c.status = 'ACTIVE'")
    List<CreditCardRecord> findActiveCardsByUser(@Param("userId") Long userId);
}

// ========================================

// RewardRuleRepository.java
package com.example.demo.repository;

import com.example.demo.entity.RewardRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RewardRuleRepository extends JpaRepository<RewardRule, Long> {
    List<RewardRule> findByCardId(Long cardId);
    List<RewardRule> findByActiveTrue();
    
    @Query("SELECT r FROM RewardRule r WHERE r.cardId = :cardId AND r.category = :category AND r.active = true")
    List<RewardRule> findActiveRulesForCardCategory(@Param("cardId") Long cardId, @Param("category") String category);
}

// ========================================

// PurchaseIntentRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.PurchaseIntentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PurchaseIntentRecordRepository extends JpaRepository<PurchaseIntentRecord, Long> {
    List<PurchaseIntentRecord> findByUserId(Long userId);
}

// ========================================

// RecommendationRecordRepository.java
package com.example.demo.repository;

import com.example.demo.entity.RecommendationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecommendationRecordRepository extends JpaRepository<RecommendationRecord, Long> {
    List<RecommendationRecord> findByUserId(Long userId);
}