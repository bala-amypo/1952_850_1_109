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
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @Transient
    private Long userId;

    @Column(nullable = false)
    @NotBlank(message = "Card name is required")
    private String cardName;

    @Column(nullable = false)
    @NotBlank(message = "Issuer is required")
    private String issuer;

    @Column(nullable = false)
    @NotBlank(message = "Card type is required")
    private String cardType;

    @Column(nullable = false)
    @NotNull(message = "Annual fee is required")
    @DecimalMin(value = "0.0", message = "Annual fee must be >= 0")
    private Double annualFee;

    @Column(nullable = false)
    @NotBlank(message = "Status is required")
    private String status = "ACTIVE";

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RewardRule> rewardRules = new HashSet<>();

    @ManyToMany(mappedBy = "favouriteCards")
    @JsonIgnore
    private Set<UserProfile> favouredByUsers = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (annualFee == null || annualFee < 0)
            throw new IllegalArgumentException("Annual fee must be >= 0");
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUser() { return user; }
    public void setUser(UserProfile user) { this.user = user; }
    
    public String getCardName() { return cardName; }
    public void setCardName(String cardName) { this.cardName = cardName; }
    
    public String getIssuer() { return issuer; }
    public void setIssuer(String issuer) { this.issuer = issuer; }
    
    public String getCardType() { return cardType; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    
    public Double getAnnualFee() { return annualFee; }
    public void setAnnualFee(Double annualFee) { this.annualFee = annualFee; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Set<RewardRule> getRewardRules() { return rewardRules; }
    public void setRewardRules(Set<RewardRule> rewardRules) { this.rewardRules = rewardRules; }
    
    public Set<UserProfile> getFavouredByUsers() { return favouredByUsers; }
    public void setFavouredByUsers(Set<UserProfile> favouredByUsers) { this.favouredByUsers = favouredByUsers; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
