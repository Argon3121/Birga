package com.test.test.service;

import com.test.test.model.Currency;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CurrencyService {
    private Map<Long, Currency> currencies = new HashMap<>();
    private Long nextId = 6L;

    public CurrencyService() {
        // ЮАНЬ - базовая валюта (курс = 1)
        currencies.put(1L, new Currency(1L, "CNY", "Chinese Yuan", "¥", BigDecimal.ONE, true, "China"));
        currencies.put(2L, new Currency(2L, "EUR", "Euro", "€", new BigDecimal("7.85"), true, "EU"));     // 1 евро = 7.85 юаня
        currencies.put(3L, new Currency(3L, "USD", "Dollar", "$", new BigDecimal("7.25"), true, "USA"));   // 1 доллар = 7.25 юаня
        currencies.put(4L, new Currency(4L, "RUB", "Ruble", "₽", new BigDecimal("0.082"), true, "Russia")); // 1 рубль = 0.082 юаня
        currencies.put(5L, new Currency(5L, "KZT", "Tenge", "₸", new BigDecimal("0.015"), true, "Kazakhstan")); // 1 тенге = 0.015 юаня
    }

    public List<Currency> getAllCurrencies() {
        return new ArrayList<>(currencies.values());
    }

    public Currency getCurrencyById(Long id) {
        return currencies.get(id);
    }

    public Currency getCurrencyByCode(String code) {
        for (var cur : currencies.values()) {
            if (cur.getCode().equalsIgnoreCase(code)) {
                return cur;
            }
        }
        return null;
    }

    public List<Currency> getActiveCurrencies() {
        List<Currency> result = new ArrayList<>();
        for (var cur : currencies.values()) {
            if (cur.isActive()) {
                result.add(cur);
            }
        }
        return result;
    }

    public Currency createCurrency(Currency currency) {
        currency.setId(nextId++);
        if (currency.getRateToCNY() == null) {
            currency.setRateToCNY(BigDecimal.ZERO);
        }
        currencies.put(currency.getId(), currency);
        return currency;
    }

    public Currency updateRate(Long id, BigDecimal rate) {
        Currency currency = currencies.get(id);
        if (currency != null) {
            currency.setRateToCNY(rate);
            currencies.put(id, currency);
            return currency;
        }
        return null;
    }

    public boolean deleteCurrency(Long id) {
        if (currencies.containsKey(id)) {
            currencies.remove(id);
            return true;
        }
        return false;
    }

    // Конвертация через юань
    public BigDecimal convert(String fromCode, String toCode, BigDecimal amount) {
        Currency from = getCurrencyByCode(fromCode);
        Currency to = getCurrencyByCode(toCode);

        if (from == null || to == null) {
            return null;
        }

        // Сначала в юань, потом в нужную валюту
        BigDecimal inCNY = amount.multiply(from.getRateToCNY());
        BigDecimal result = inCNY.divide(to.getRateToCNY(), 2, RoundingMode.HALF_UP);

        return result;
    }
}