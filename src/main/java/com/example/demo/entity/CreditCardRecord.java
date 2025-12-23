package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "credit_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile user;

    @NotBlank
    private String cardName;

    @NotBlank
    private String issuer;

    @NotBlank
    private String cardType;

    @NotNull
    @DecimalMin("0.0")
    private Double annualFee;

    @Column(nullable = false)
    private String status = "ACTIVE";

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RewardRule> rewardRules = new HashSet<>();

    @ManyToMany(mappedBy = "favouriteCards")
    @JsonIgnore
    private Set<UserProfile> favouredByUsers = new HashSet<>();

    @PrePersist
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
