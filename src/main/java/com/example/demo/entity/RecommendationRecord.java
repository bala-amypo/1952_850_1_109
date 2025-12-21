package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "purchase_intent_id", nullable = false)
    private PurchaseIntentRecord purchaseIntent;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recommended_card_id", nullable = false)
    private CreditCardRecord recommendedCard;

    @Column(nullable = false)
    private Double expectedRewardValue;

    @Lob
    private String calculationDetailsJson;

    private LocalDateTime recommendedAt;

    @PrePersist
    protected void onCreate() {
        if (expectedRewardValue == null || expectedRewardValue < 0)
            throw new IllegalArgumentException("Expected reward value must be >= 0");
        recommendedAt = LocalDateTime.now();
    }
}
