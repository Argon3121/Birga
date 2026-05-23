package com.test.test.service;

import com.test.test.model.Seller;
import com.test.test.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CurrencyService currencyService;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public List<Seller> getActiveSellers() {
        return sellerRepository.findByActiveTrue();
    }

    public Seller createSeller(Seller seller) {
        if (seller.getBalance() == null) {
            seller.setBalance(BigDecimal.ZERO);
        }
        return sellerRepository.save(seller);
    }

    public Seller updateRates(Long id, String code, BigDecimal buy, BigDecimal sell) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller != null) {
            switch (code.toUpperCase()) {
                case "USD":
                    seller.setUsdBuyRate(buy);
                    seller.setUsdSellRate(sell);
                    break;
                case "EUR":
                    seller.setEurBuyRate(buy);
                    seller.setEurSellRate(sell);
                    break;
                case "RUB":
                    seller.setRubBuyRate(buy);
                    seller.setRubSellRate(sell);
                    break;
                case "KZT":
                    seller.setKztBuyRate(buy);
                    seller.setKztSellRate(sell);
                    break;
            }
            return sellerRepository.save(seller);
        }
        return null;
    }

    public BigDecimal buyCurrency(Long id, String code, BigDecimal amount, String payCode) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null || !seller.isActive()) {
            return null;
        }

        BigDecimal rate = getSellRate(seller, code);
        if (rate == null) {
            return null;
        }

        if (currencyService.getCurrencyByCode(payCode) == null) {
            return null;
        }

        BigDecimal cost = amount.multiply(rate);

        if (seller.getBalance().compareTo(cost) >= 0) {
            seller.setBalance(seller.getBalance().subtract(cost));
            sellerRepository.save(seller);
            return cost;
        }
        return null;
    }

    public BigDecimal sellCurrency(Long id, String code, BigDecimal amount, String getCode) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null || !seller.isActive()) {
            return null;
        }

        BigDecimal rate = getBuyRate(seller, code);
        if (rate == null) {
            return null;
        }

        if (currencyService.getCurrencyByCode(getCode) == null) {
            return null;
        }

        BigDecimal result = amount.multiply(rate);
        seller.setBalance(seller.getBalance().add(result));
        sellerRepository.save(seller);
        return result;
    }

    public Seller depositBalance(Long id, BigDecimal amount) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        if (seller == null) {
            return null;
        }
        seller.setBalance(seller.getBalance().add(amount));
        return sellerRepository.save(seller);
    }

    public boolean deleteSeller(Long id) {
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Вспомогательные методы для получения курсов
    private BigDecimal getBuyRate(Seller seller, String code) {
        switch (code.toUpperCase()) {
            case "USD": return seller.getUsdBuyRate();
            case "EUR": return seller.getEurBuyRate();
            case "RUB": return seller.getRubBuyRate();
            case "KZT": return seller.getKztBuyRate();
            default: return null;
        }
    }

    private BigDecimal getSellRate(Seller seller, String code) {
        switch (code.toUpperCase()) {
            case "USD": return seller.getUsdSellRate();
            case "EUR": return seller.getEurSellRate();
            case "RUB": return seller.getRubSellRate();
            case "KZT": return seller.getKztSellRate();
            default: return null;
        }
    }
}