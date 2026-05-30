package com.test.test.service;

import com.test.test.model.Trade;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class TradeService {


    public BigDecimal compliteTrade(Trade trade) {
        BigDecimal rub = trade.getValue_t();
        BigDecimal kurs = new BigDecimal("71");
        BigDecimal usd = rub.divide(kurs, 2, RoundingMode.HALF_UP);

        return usd;
    }
}