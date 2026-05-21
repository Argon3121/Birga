package com.test.test.service;


import com.test.test.model.Exchange;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExchangeService {
    private Map<Long, Exchange> exchanges = new HashMap<>();
    private Long nextId = 1L;

    public ExchangeService() {
        exchanges.put(1L, new Exchange(1L,"ByBit", "adasd", "https://b.b", "Nice", true, 2009L));
        exchanges.put(2L, new Exchange(1L,"ByBit", "Japan", "https://b.b", "Nice", true, 2009L));
        exchanges.put(3L, new Exchange(1L,"ByBit", "Machasransk", "https://b.b", "Nice", true, 2009L));
        exchanges.put(4L, new Exchange(1L,"ByBit", "Japasdasdan", "https://b.b", "Nice", true, 2009L));
        exchanges.put(5L, new Exchange(1L,"ByBit", "asdasd", "https://b.b", "Nice", true, 2009L));
        exchanges.put(6L, new Exchange(1L,"ByBit", "asdasd", "https://b.b", "Nice", true, 2009L));
        exchanges.put(7L, new Exchange(1L,"ByBit", "Machasransk", "https://b.b", "Nice", true, 2009L));
        exchanges.put(8L, new Exchange(1L,"ByBit", "Machasransk", "https://b.b", "Nice", true, 2009L));
        exchanges.put(9L, new Exchange(1L,"ByBit", "asd", "https://b.b", "Nice", true, 2009L));
    }
    // http://localhost:8080/api/exchanges/Japan
    public List<Exchange> getAllExchanges() {
        return new ArrayList<>(exchanges.values());
    }
    public List<Exchange> getExchangesByCountry(String country) {
        List<Exchange> result = new ArrayList<>();
        for (var ex : exchanges.values()) {
            if (ex.getCountry().equals(country)) {
                result.add(ex);
            }
        }
        return result;
    }
    public List<Exchange> getExchangesByfounded(Long founded) {
        List<Exchange> result = new ArrayList<>();
        for (var ex : exchanges.values()) {
            if (ex.getFounded().equals(founded)) {
                result.add(ex);
            }
        }
        return result;
    }
}
