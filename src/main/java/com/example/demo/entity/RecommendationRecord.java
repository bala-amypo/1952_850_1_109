package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recommendation_records")
public class RecommendationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String recommendedProduct;

    private LocalDateTime createdAt;

    public RecommendationRecord() {}

    public RecommendationRecord(Long userId, String recommendedProduct, LocalDateTime createdAt) {
        this.userId = userId;
        this.recommendedProduct = recommendedProduct;
        this.createdAt = createdAt;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getRecommendedProduct() { return recommendedProduct; }
    public void setRecommendedProduct(String recommendedProduct) { this.recommendedProduct = recommendedProduct; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}