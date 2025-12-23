package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecommendationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "purchase_intent_id", nullable = false)
    @JsonIgnore
    private PurchaseIntentRecord purchaseIntent;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recommended_card_id", nullable = false)
    @JsonIgnore
    private CreditCardRecord recommendedCard;

    @NotNull
    @DecimalMin("0.0")
    private Double expectedRewardValue;

    @Lob
    private String calculationDetailsJson;

    private LocalDateTime recommendedAt;

    @PrePersist
    void onCreate() {
        recommendedAt = LocalDateTime.now();
    }
}
