package com.test.test.service;

import com.test.test.model.Seller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerService {

    @Autowired
    private CurrencyService currencyService;

    private Map<Long, Seller> sellers = new HashMap<>();
    private Long nextId = 3L;

    public SellerService() {
        Seller s1 = new Seller();
        s1.setId(1L);
        s1.setName("Exchange 1");
        s1.setEmail("ex1@mail.com");
        s1.setPhone("123456");
        s1.setBalance(new BigDecimal("100000")); // 100000 юаней
        s1.setActive(true);

        // Курсы в ЮАНЯХ
        s1.getBuyRates().put("USD", new BigDecimal("7.20"));   // покупаем доллар за 7.20 юаня
        s1.getSellRates().put("USD", new BigDecimal("7.30"));  // продаем доллар за 7.30 юаня
        s1.getBuyRates().put("EUR", new BigDecimal("7.80"));   // покупаем евро за 7.80 юаня
        s1.getSellRates().put("EUR", new BigDecimal("7.90"));  // продаем евро за 7.90 юаня

        sellers.put(1L, s1);
    }

    public List<Seller> getAllSellers() {
        return new ArrayList<>(sellers.values());
    }

    public Seller getSellerById(Long id) {
        return sellers.get(id);
    }

    public List<Seller> getActiveSellers() {
        List<Seller> result = new ArrayList<>();
        for (var sel : sellers.values()) {
            if (sel.isActive()) {
                result.add(sel);
            }
        }
        return result;
    }

    public Seller createSeller(Seller seller) {
        seller.setId(nextId++);
        if (seller.getBalance() == null) {
            seller.setBalance(BigDecimal.ZERO);
        }
        if (seller.getBuyRates() == null) {
            seller.setBuyRates(new HashMap<>());
        }
        if (seller.getSellRates() == null) {
            seller.setSellRates(new HashMap<>());
        }
        sellers.put(seller.getId(), seller);
        return seller;
    }

    public Seller updateRates(Long id, String code, BigDecimal buy, BigDecimal sell) {
        Seller seller = sellers.get(id);
        if (seller != null) {
            if (buy != null) {
                seller.getBuyRates().put(code.toUpperCase(), buy);
            }
            if (sell != null) {
                seller.getSellRates().put(code.toUpperCase(), sell);
            }
            sellers.put(id, seller);
            return seller;
        }
        return null;
    }

    // Купить валюту у продавца
    public BigDecimal buyCurrency(Long id, String code, BigDecimal amount, String payCode) {
        Seller seller = sellers.get(id);
        if (seller == null || !seller.isActive()) {
            return null;
        }

        BigDecimal rate = seller.getSellRates().get(code.toUpperCase());
        if (rate == null) {
            return null;
        }

        if (currencyService.getCurrencyByCode(payCode) == null) {
            return null;
        }

        BigDecimal cost = amount.multiply(rate);

        if (seller.getBalance().compareTo(cost) >= 0) {
            seller.setBalance(seller.getBalance().subtract(cost));
            sellers.put(id, seller);
            return cost;
        }

        return null;
    }

    // Продать валюту продавцу
    public BigDecimal sellCurrency(Long id, String code, BigDecimal amount, String getCode) {
        Seller seller = sellers.get(id);
        if (seller == null || !seller.isActive()) {
            return null;
        }

        BigDecimal rate = seller.getBuyRates().get(code.toUpperCase());
        if (rate == null) {
            return null;
        }

        if (currencyService.getCurrencyByCode(getCode) == null) {
            return null;
        }

        BigDecimal result = amount.multiply(rate);

        seller.setBalance(seller.getBalance().add(result));
        sellers.put(id, seller);

        return result;
    }

    public Seller depositBalance(Long id, BigDecimal amount) {
        Seller seller = sellers.get(id);
        if (seller == null) {
            return null;
        }
        seller.setBalance(seller.getBalance().add(amount));
        sellers.put(id, seller);
        return seller;
    }

    public boolean deleteSeller(Long id) {
        if (sellers.containsKey(id)) {
            sellers.remove(id);
            return true;
        }
        return false;
    }
}