package com.test.test.service;


import com.test.test.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    private Map<Long, Customer> customers = new HashMap<>();
    private Long nextId = 1L;

    public CustomerService() {
        customers.put(1L, new Customer("Damir","Gorin", "Vladimirovich", "gorin.damirr@gmail.com", 0.0));
        customers.put(2L, new Customer("Oleg","Gorin", "Vladimirovich", "gorin.damirr@gmail.com", 0.0));
        customers.put(3L, new Customer("Vova","Adidas", "Miroslavovich", "gorin.damirr@gmail.com", 0.0));

    }
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }
    public List<Customer> getCustomersByName(String name) {
        List<Customer> result = new ArrayList<>();
        for (var cu : customers.values()) {
            if (cu.getName().equals(name)) {
                result.add(cu);
            }
        }
        return result;
    }

}
