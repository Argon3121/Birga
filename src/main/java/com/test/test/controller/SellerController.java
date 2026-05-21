package com.test.test.controller;

import com.test.test.model.Seller;
import com.test.test.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    // 1. Получить всех продавцов
    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        return ResponseEntity.ok(sellerService.getAllSellers());
    }

    // 2. Получить продавца по ID
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Seller seller = sellerService.getSellerById(id);
        if (seller != null) {
            return ResponseEntity.ok(seller);
        }
        return ResponseEntity.notFound().build();
    }

    // 3. Получить активных продавцов
    @GetMapping("/active")
    public ResponseEntity<List<Seller>> getActiveSellers() {
        return ResponseEntity.ok(sellerService.getActiveSellers());
    }

    // 4. Создать нового продавца
    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        Seller created = sellerService.createSeller(seller);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // 5. Обновить курс продавца
    @PutMapping("/{id}/rate")
    public ResponseEntity<Seller> updateRate(
            @PathVariable Long id,
            @RequestParam String code,
            @RequestParam BigDecimal buy,
            @RequestParam BigDecimal sell) {
        Seller updated = sellerService.updateRates(id, code, buy, sell);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // 6. КУПИТЬ валюту у продавца (платишь в одной валюте, получаешь другую)
    @PostMapping("/{id}/buy")
    public ResponseEntity<BigDecimal> buyCurrency(
            @PathVariable Long id,
            @RequestParam String code,
            @RequestParam BigDecimal amount,
            @RequestParam String payCode) {
        BigDecimal result = sellerService.buyCurrency(id, code, amount, payCode);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    // 7. ПРОДАТЬ валюту продавцу (отдаешь одну валюту, получаешь другую)
    @PostMapping("/{id}/sell")
    public ResponseEntity<BigDecimal> sellCurrency(
            @PathVariable Long id,
            @RequestParam String code,
            @RequestParam BigDecimal amount,
            @RequestParam String getCode) {
        BigDecimal result = sellerService.sellCurrency(id, code, amount, getCode);
        if (result != null) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().build();
    }

    // 8. Пополнить баланс продавца
    @PutMapping("/{id}/deposit")
    public ResponseEntity<Seller> depositBalance(
            @PathVariable Long id,
            @RequestParam BigDecimal amount) {
        Seller updated = sellerService.depositBalance(id, amount);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // 9. Удалить продавца
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        if (sellerService.deleteSeller(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}