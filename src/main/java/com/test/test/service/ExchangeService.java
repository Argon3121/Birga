package com.test.test.service;

import com.test.test.model.Exchange;
import com.test.test.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository repo;

    public List<Exchange> getAllExchanges() {  // ← исправлено
        return repo.findAll();
    }

    public List<Exchange> getExchangesByCountry(String country) {  // ← исправлено
        return repo.findByCountry(country);
    }

    public List<Exchange> getExchangesByFounded(Long founded) {  // ← исправлено
        return repo.findByFounded(founded);
    }

    public Exchange createExchange(Exchange exchange) {  // ← исправлено
        return repo.save(exchange);
    }

    public void deleteExchange(Long id) {  // ← исправлено
        repo.deleteById(id);
    }
}