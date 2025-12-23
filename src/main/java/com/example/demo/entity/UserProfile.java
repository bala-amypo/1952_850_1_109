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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String userId;

    @NotBlank
    private String fullName;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    private String role = "USER";

    @Column(nullable = false)
    private Boolean active = true;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<CreditCardRecord> creditCards = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PurchaseIntentRecord> purchaseIntents = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
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
    void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
