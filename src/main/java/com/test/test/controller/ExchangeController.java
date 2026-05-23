package com.test.test.controller;

import com.test.test.model.Exchange;
import com.test.test.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/exchanges")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping
    public ResponseEntity<List<Exchange>> getAllEx() {
        return ResponseEntity.ok(exchangeService.getAllExchanges());  // ← исправлено
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Exchange>> getExByCountry(@PathVariable String country) {
        return ResponseEntity.ok(exchangeService.getExchangesByCountry(country));  // ← исправлено
    }

    @GetMapping("/founded/{founded}")
    public ResponseEntity<List<Exchange>> getExByFounded(@PathVariable Long founded) {
        return ResponseEntity.ok(exchangeService.getExchangesByFounded(founded));  // ← исправлено
    }

    @PostMapping
    public ResponseEntity<Exchange> createExchange(@RequestBody Exchange exchange) {
        return ResponseEntity.ok(exchangeService.createExchange(exchange));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExchange(@PathVariable Long id) {
        exchangeService.deleteExchange(id);
        return ResponseEntity.noContent().build();
    }
}