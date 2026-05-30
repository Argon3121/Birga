package com.test.test.controller;

import com.test.test.model.Exchange;
import com.test.test.model.Trade;
import com.test.test.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(exchangeService.getAllExchanges());
    }

    @GetMapping("/country/{country}")
    public ResponseEntity<List<Exchange>> getExByCountry(@PathVariable String country) {
        return ResponseEntity.ok(exchangeService.getExchangesByCountry(country));
    }

    @GetMapping("/founded/{founded}")
    public ResponseEntity<List<Exchange>> getExByFounded(@PathVariable Long founded) {
        return ResponseEntity.ok(exchangeService.getExchangesByFounded(founded));
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

    @PostMapping("/create_exchange")
    @ResponseStatus(HttpStatus.CREATED)
    public Exchange create(@RequestBody Exchange exchange) {
        return exchangeService.createExchange(exchange);
    }




}