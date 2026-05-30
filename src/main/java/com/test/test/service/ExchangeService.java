package com.test.test.service;

import com.test.test.model.Exchange;
import com.test.test.model.Trade;
import com.test.test.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository repo;

    public List<Exchange> getAllExchanges() {
        return repo.findAll();
    }

    public List<Exchange> getExchangesByCountry(String country) {
        return repo.findByCountry(country);
    }

    public List<Exchange> getExchangesByFounded(Long founded) {
        return repo.findByFounded(founded);
    }

    public Exchange createExchange(Exchange exchange) {
        return repo.save(exchange);
    }

    public void deleteExchange(Long id) {
        repo.deleteById(id);
    }


}