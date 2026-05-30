package com.test.test.service;

import com.test.test.model.Currency;
import com.test.test.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        return currencyRepository.save(currency);
    }

    public void deleteCurrency(Long id) {
        currencyRepository.deleteById(id);
    }
}