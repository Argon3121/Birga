package com.test.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "sellers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
    private String phone;
    private BigDecimal balance;
    private boolean active;

    // Курсы для USD
    private BigDecimal usdBuyRate;
    private BigDecimal usdSellRate;

    // Курсы для EUR
    private BigDecimal eurBuyRate;
    private BigDecimal eurSellRate;

    // Курсы для RUB
    private BigDecimal rubBuyRate;
    private BigDecimal rubSellRate;

    // Курсы для KZT
    private BigDecimal kztBuyRate;
    private BigDecimal kztSellRate;
}