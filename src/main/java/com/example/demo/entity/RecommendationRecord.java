package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendations")
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @ManyToOne(optional = true)
    @JoinColumn(name = "purchase_intent_id", nullable = false)
    private PurchaseIntentRecord purchaseIntent;

    @ManyToOne(optional = true)
    @JoinColumn(name = "recommended_card_id", nullable = false)
    private CreditCardRecord recommendedCard;

    @Transient
    private Long userId;
    
    @Transient
    private Long purchaseIntentId;
    
    @Transient
    private Long recommendedCardId;

    @Column(nullable = false)
    @NotNull(message = "Expected reward value is required")
    @DecimalMin(value = "0.0", message = "Expected reward value must be >= 0")
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

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUser() { return user; }
    public void setUser(UserProfile user) { this.user = user; }
    
    public PurchaseIntentRecord getPurchaseIntent() { return purchaseIntent; }
    public void setPurchaseIntent(PurchaseIntentRecord purchaseIntent) { this.purchaseIntent = purchaseIntent; }
    
    public CreditCardRecord getRecommendedCard() { return recommendedCard; }
    public void setRecommendedCard(CreditCardRecord recommendedCard) { this.recommendedCard = recommendedCard; }
    
    public Double getExpectedRewardValue() { return expectedRewardValue; }
    public void setExpectedRewardValue(Double expectedRewardValue) { this.expectedRewardValue = expectedRewardValue; }
    
    public String getCalculationDetailsJson() { return calculationDetailsJson; }
    public void setCalculationDetailsJson(String calculationDetailsJson) { this.calculationDetailsJson = calculationDetailsJson; }
    
    public LocalDateTime getRecommendedAt() { return recommendedAt; }
    public void setRecommendedAt(LocalDateTime recommendedAt) { this.recommendedAt = recommendedAt; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getPurchaseIntentId() { return purchaseIntentId; }
    public void setPurchaseIntentId(Long purchaseIntentId) { this.purchaseIntentId = purchaseIntentId; }
    
    public Long getRecommendedCardId() { return recommendedCardId; }
    public void setRecommendedCardId(Long recommendedCardId) { this.recommendedCardId = recommendedCardId; }
}
