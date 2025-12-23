package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_profiles")
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = true)
    private String userId; // business identifier

    private String fullName;

    @Column(unique = true, nullable = true)
    private String email;

    @Column(nullable = true)
    private String password;

    @Column(nullable = true)
    private String role = "USER";

    @Column(nullable = true)
    private Boolean active = true;

    private LocalDateTime createdAt;

    // Bidirectional relationships
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<CreditCardRecord> creditCards = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PurchaseIntentRecord> purchaseIntents = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<RecommendationRecord> recommendations = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "user_favourite_cards",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @JsonIgnore
    private Set<CreditCardRecord> favouriteCards = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public UserProfile(String userId, String fullName, String email, String password, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public Set<CreditCardRecord> getCreditCards() { return creditCards; }
    public void setCreditCards(Set<CreditCardRecord> creditCards) { this.creditCards = creditCards; }
    
    public Set<PurchaseIntentRecord> getPurchaseIntents() { return purchaseIntents; }
    public void setPurchaseIntents(Set<PurchaseIntentRecord> purchaseIntents) { this.purchaseIntents = purchaseIntents; }
    
    public Set<RecommendationRecord> getRecommendations() { return recommendations; }
    public void setRecommendations(Set<RecommendationRecord> recommendations) { this.recommendations = recommendations; }
    
    public Set<CreditCardRecord> getFavouriteCards() { return favouriteCards; }
    public void setFavouriteCards(Set<CreditCardRecord> favouriteCards) { this.favouriteCards = favouriteCards; }
}
