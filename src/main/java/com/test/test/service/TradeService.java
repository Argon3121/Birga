package com.test.test.service;

import com.test.test.model.Trade;
import com.test.test.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    // ДОБАВЛЕННЫЙ МЕТОД - получает все сделки
    public List<Trade> getAllTrades() {
        try {
            return tradeRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public BigDecimal compliteTrade(Trade trade) {
        // Сохраняем сделку в БД
        if (tradeRepository != null) {
            tradeRepository.save(trade);
        }

        BigDecimal rub = trade.getValue_t();
        BigDecimal kurs = new BigDecimal("71");
        BigDecimal usd = rub.divide(kurs, 2, RoundingMode.HALF_UP);
        return usd;
    }
}