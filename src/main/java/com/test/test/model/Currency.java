package com.test.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;        // CNY, EUR, USD, RUB

    private String name;        // Юань, Евро, Доллар
    private String symbol;      // ¥, €, $
    private BigDecimal rateToCNY;  // Курс к юаню
    private boolean active;
    private String country;
}