package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(
        name = "reward_rules",
        uniqueConstraints = @UniqueConstraint(columnNames = {"card_id", "category"})
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RewardRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "card_id", nullable = false)
    @JsonIgnore
    private CreditCardRecord card;

    @NotBlank
    private String category;

    @NotBlank
    private String rewardType;

    @NotNull
    @DecimalMin("0.1")
    private Double multiplier;

    @Column(nullable = false)
    private Boolean active = true;
}
