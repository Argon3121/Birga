
//ИЗУЧИТЬ РЕКВЕСТ ПАРАМ
// ИЗУЧИТЬ

package com.test.test.controller;

import com.test.test.model.Currency;
import com.test.test.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/{id}/rate")
    public ResponseEntity<Currency> updateRate(@PathVariable Long id, @RequestParam BigDecimal rate) {
        Currency updated = currencyService.updateRate(id, rate);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCur(@PathVariable Long id) {
        if (currencyService.deleteCurrency(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> convert(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        BigDecimal result = currencyService.convert(from, to, amount);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    // НОВЫЙ ЭНДПОИНТ: /trade?from=EUR&to=USD&amount=100
    @GetMapping("/trade")
    public ResponseEntity<Map<String, Object>> trade(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam BigDecimal amount) {

        BigDecimal result = currencyService.convert(from, to, amount);

        if (result != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("from", from.toUpperCase());
            response.put("to", to.toUpperCase());
            response.put("amount", amount);
            response.put("result", result);
            response.put("formatted", amount + " " + from.toUpperCase() + " = " + result + " " + to.toUpperCase());
            return ResponseEntity.ok(response);
        }

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Валюта не найдена");
        error.put("from", from);
        error.put("to", to);
        return ResponseEntity.badRequest().body(error);
    }
}