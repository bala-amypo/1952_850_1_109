package com.example.demo.repository;

import com.example.demo.entity.RewardRule;
import com.example.demo.entity.CreditCardRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRuleRepository extends JpaRepository<RewardRule, Long> {

    List<RewardRule> findByCard(CreditCardRecord card);

    List<RewardRule> findByActiveTrue();

    @Query("SELECT r FROM RewardRule r WHERE r.card = :card AND r.category = :category AND r.active = true")
    List<RewardRule> findActiveRulesForCardCategory(
            @Param("card") CreditCardRecord card,
            @Param("category") String category);
}
