package com.test.test.controller;

import com.test.test.service.CurrencyService;
import com.test.test.service.CustomerService;
import com.test.test.service.ExchangeService;
import com.test.test.service.SellerService;
import com.test.test.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private TradeService tradeService;

    @GetMapping("/")
    public String home(Model model) {
        // Данные для дашборда
        model.addAttribute("recentCurrencies", currencyService.getAllCurrencies());
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("exchanges", exchangeService.getAllExchanges());
        model.addAttribute("sellers", sellerService.getAllSellers());

        // Статистика для дашборда
        model.addAttribute("currencyCount", currencyService.getAllCurrencies().size());
        model.addAttribute("customerCount", customerService.getAllCustomers().size());
        model.addAttribute("exchangeCount", exchangeService.getAllExchanges().size());
        model.addAttribute("tradeCount", tradeService.getAllTrades().size());

        return "index";
    }

    @GetMapping("/currencies")
    public String currencies(Model model) {
        model.addAttribute("currencies", currencyService.getAllCurrencies());
        return "currencies";
    }

    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers", customerService.getAllCustomers());
        return "customers";
    }

    @GetMapping("/exchanges")
    public String exchanges(Model model) {
        model.addAttribute("exchanges", exchangeService.getAllExchanges());
        return "exchanges";
    }

    @GetMapping("/sellers")
    public String sellers(Model model) {
        model.addAttribute("sellers", sellerService.getAllSellers());
        return "sellers";
    }

    @GetMapping("/trades")
    public String trades(Model model) {
        model.addAttribute("trades", tradeService.getAllTrades());
        return "trades";
    }
}