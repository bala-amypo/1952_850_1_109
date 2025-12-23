package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "reward_rules",
       uniqueConstraints = @UniqueConstraint(columnNames = {"card_id", "category"}))
@NoArgsConstructor
@AllArgsConstructor
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "card_id", nullable = true)
    private CreditCardRecord card;

    @Transient
    private Long cardId;

    @Column(nullable = false)
    @NotBlank(message = "Category is required")
    private String category;

    @Column(nullable = false)
    @NotBlank(message = "Reward type is required")
    private String rewardType;

    @Column(nullable = false)
    @NotNull(message = "Multiplier is required")
    @DecimalMin(value = "0.1", message = "Multiplier must be > 0")
    private Double multiplier;

    @Column(nullable = false)
    @NotNull(message = "Active status is required")
    private Boolean active = true;

    @PrePersist
    @PreUpdate
    protected void validate() {
        if (multiplier == null || multiplier <= 0)
            throw new IllegalArgumentException("Price multiplier must be > 0");
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public CreditCardRecord getCard() { return card; }
    public void setCard(CreditCardRecord card) { this.card = card; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getRewardType() { return rewardType; }
    public void setRewardType(String rewardType) { this.rewardType = rewardType; }
    
    public Double getMultiplier() { return multiplier; }
    public void setMultiplier(Double multiplier) { this.multiplier = multiplier; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Long getCardId() { return cardId; }
    public void setCardId(Long cardId) { this.cardId = cardId; }
}
