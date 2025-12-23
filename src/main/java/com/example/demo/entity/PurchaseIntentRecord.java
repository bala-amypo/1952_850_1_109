package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchase_intents")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseIntentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile user;

    @Column(nullable = false)
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.01", message = "Amount must be > 0")
    private Double amount;

    @Column(nullable = false)
    @NotBlank(message = "Category is required")
    private String category;

    private String merchant;

    private LocalDateTime intentDate;

    @OneToMany(mappedBy = "purchaseIntent", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RecommendationRecord> recommendations = new HashSet<>();

    @PrePersist
    @PreUpdate
    protected void validate() {
        if (amount == null || amount <= 0)
            throw new IllegalArgumentException("Purchase amount must be > 0");
        if (intentDate == null)
            intentDate = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public UserProfile getUser() { return user; }
    public void setUser(UserProfile user) { this.user = user; }
    
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public String getMerchant() { return merchant; }
    public void setMerchant(String merchant) { this.merchant = merchant; }
    
    public LocalDateTime getIntentDate() { return intentDate; }
    public void setIntentDate(LocalDateTime intentDate) { this.intentDate = intentDate; }
    
    public Set<RecommendationRecord> getRecommendations() { return recommendations; }
    public void setRecommendations(Set<RecommendationRecord> recommendations) { this.recommendations = recommendations; }
}
