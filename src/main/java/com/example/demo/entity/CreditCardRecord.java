package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credit_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @Column(nullable = false)
    private String cardName;

    @Column(nullable = false)
    private String issuer;

    @Column(nullable = false)
    private String cardType;

    @Column(nullable = false)
    private Double annualFee;

    @Column(nullable = false)
    private String status = "ACTIVE";

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<RewardRule> rewardRules = new HashSet<>();

    @ManyToMany(mappedBy = "favouriteCards")
    private Set<UserProfile> favouredByUsers = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (annualFee == null || annualFee < 0)
            throw new IllegalArgumentException("Annual fee must be >= 0");
        createdAt = LocalDateTime.now();
    }
}
