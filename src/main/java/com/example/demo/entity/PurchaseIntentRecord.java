package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "purchase_intents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseIntentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserProfile user;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private String category;

    private String merchant;

    private LocalDateTime intentDate;

    @OneToMany(mappedBy = "purchaseIntent", cascade = CascadeType.ALL)
    private Set<RecommendationRecord> recommendations = new HashSet<>();

    @PrePersist
    @PreUpdate
    protected void validate() {
        if (amount == null || amount <= 0)
            throw new IllegalArgumentException("Purchase amount must be > 0");
        if (intentDate == null)
            intentDate = LocalDateTime.now();
    }
}
