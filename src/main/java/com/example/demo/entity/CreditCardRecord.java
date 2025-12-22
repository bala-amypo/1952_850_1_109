package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String cardName;
    private String issuer;
    private String cardType;
    private Double annualFee;
    private String status;

    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "favouriteCards")
    private Set<UserProfile> favouredByUsers;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
