package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seller {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private BigDecimal balance;
    private boolean active;

    // Курсы продажи (сколько стоит валюта)
    private Map<String, BigDecimal> sellRates = new HashMap<>();

    // Курсы покупки (за сколько продавец покупает валюту)
    private Map<String, BigDecimal> buyRates = new HashMap<>();
}
/* Купить 50 долларов за евро
POST /api/sellers/1/buy?code=USD&amount=50&payCode=EUR
50 × 1.02 = 51 евро

Купить 1000 рублей за доллары
POST /api/sellers/1/buy?code=RUB&amount=1000&payCode=USD
1000 × 0.0112 = 11.2 доллара

Купить у другого продавца (ID=2)
POST /api/sellers/2/buy?code=EUR&amount=100&payCode=USD
У продавца №2 курс евро = 1.12, значит 100 × 1.12 = 112 долларов*/