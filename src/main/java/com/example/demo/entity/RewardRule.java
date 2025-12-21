package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reward_rules",
       uniqueConstraints = @UniqueConstraint(columnNames = {"card_id", "category"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    private CreditCardRecord card;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String rewardType;

    @Column(nullable = false)
    private Double multiplier;

    @Column(nullable = false)
    private Boolean active = true;

    @PrePersist
    @PreUpdate
    protected void validate() {
        if (multiplier == null || multiplier <= 0)
            throw new IllegalArgumentException("Price multiplier must be > 0");
    }
}
