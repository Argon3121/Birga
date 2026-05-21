package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    private Long id;
    private String code;        // CNY, EUR, USD, RUB
    private String name;        // Юань, Евро, Доллар
    private String symbol;      // ¥, €, $
    private BigDecimal rateToCNY;  // Курс к юаню
    private boolean active;
    private String country;
}

/*# Конвертация
GET /api/currencies/convert?from=EUR&to=CNY&amount=100
→ 785

# Trade
GET /api/currencies/trade/EUR/to/USD?amount=100
→ { "from": "EUR", "to": "USD", "amount": 100, "result": 108.28 }

# Купить 100 евро за юани у продавца
POST /api/sellers/1/buy?code=EUR&amount=100&payCode=CNY
→ 790

# Продать 100 евро за юани
POST /api/sellers/1/sell?code=EUR&amount=100&getCode=CNY
→ 780*/