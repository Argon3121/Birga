package com.test.test.service;

import com.test.test.model.Currency;
import com.test.test.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getCurrencyById(Long id) {
        return currencyRepository.findById(id).orElse(null);
    }

    public Currency getCurrencyByCode(String code) {
        return currencyRepository.findByCode(code.toUpperCase()).orElse(null);
    }

    public List<Currency> getActiveCurrencies() {
        return currencyRepository.findByActiveTrue();
    }

    public Currency createCurrency(Currency currency) {
        currency.setCode(currency.getCode().toUpperCase());
        if (currency.getRateToCNY() == null) {
            currency.setRateToCNY(BigDecimal.ZERO);
        }
        return currencyRepository.save(currency);
    }

    public Currency updateRate(Long id, BigDecimal rate) {
        Currency currency = currencyRepository.findById(id).orElse(null);
        if (currency != null) {
            currency.setRateToCNY(rate);
            return currencyRepository.save(currency);
        }
        return null;
    }

    public boolean deleteCurrency(Long id) {
        if (currencyRepository.existsById(id)) {
            currencyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public BigDecimal convert(String fromCode, String toCode, BigDecimal amount) {
        Currency from = getCurrencyByCode(fromCode);
        Currency to = getCurrencyByCode(toCode);

        if (from == null || to == null) {
            return null;
        }

        BigDecimal inCNY = amount.multiply(from.getRateToCNY());
        BigDecimal result = inCNY.divide(to.getRateToCNY(), 2, RoundingMode.HALF_UP);
        return result;
    }
}