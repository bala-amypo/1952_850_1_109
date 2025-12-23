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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseIntentRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserProfile user;

    @NotNull
    @DecimalMin("0.01")
    private Double amount;

    @NotBlank
    private String category;

    private String merchant;

    private LocalDateTime intentDate;

    @OneToMany(mappedBy = "purchaseIntent", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<RecommendationRecord> recommendations = new HashSet<>();

    @PrePersist
    void onCreate() {
        intentDate = LocalDateTime.now();
    }
}
