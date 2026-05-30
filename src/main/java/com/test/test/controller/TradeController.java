package com.test.test.controller;

import com.test.test.model.Trade;
import com.test.test.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/complite_exchange")
    public BigDecimal complite(@RequestBody Trade trade) {
        return tradeService.compliteTrade(trade);
    }
}