package com.test.test.controller;

import com.test.test.model.Currency;
import com.test.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> getAllCur() {
        return ResponseEntity.ok(currencyService.getAllCurrencies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Currency> getCurById(@PathVariable Long id) {
        Currency currency = currencyService.getCurrencyById(id);
        if (currency != null) {
            return ResponseEntity.ok(currency);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Currency> getCurByCode(@PathVariable String code) {
        Currency currency = currencyService.getCurrencyByCode(code);
        if (currency != null) {
            return ResponseEntity.ok(currency);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/active")
    public ResponseEntity<List<Currency>> getActiveCur() {
        return ResponseEntity.ok(currencyService.getActiveCurrencies());
    }

    @PostMapping
    public ResponseEntity<Currency> createCur(@RequestBody Currency currency) {
        Currency created = currencyService.createCurrency(currency);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCur(@PathVariable Long id) {
        currencyService.deleteCurrency(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create_currency")
    @ResponseStatus(HttpStatus.CREATED)
    public Currency create(@RequestBody Currency currency) {
        return currencyService.createCurrency(currency);
    }
}