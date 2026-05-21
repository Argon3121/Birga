package com.test.test.service;

import com.test.test.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private Map<Long, Customer> customers = new HashMap<>();
    private Long nextId = 4L;

    public CustomerService() {
        customers.put(1L, new Customer("Damir", "Gorin", "Vladimirovich", "gorin.damirr@gmail.com", 0.0));
        customers.put(2L, new Customer("Oleg", "Gorin", "Vladimirovich", "gorin.damirr@gmail.com", 0.0));
        customers.put(3L, new Customer("Vova", "Adidas", "Miroslavovich", "gorin.damirr@gmail.com", 0.0));
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomerById(Long id) {
        return customers.get(id);
    }

    public List<Customer> getCustomersByName(String name) {
        return customers.values().stream()
                .filter(c -> c.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Customer createCustomer(Customer customer) {
        customer.setBalance(0.0);
        customers.put(nextId, customer);
        nextId++;
        return customer;
    }

    public Customer updateBalance(Long id, double amount) {
        Customer customer = customers.get(id);
        if (customer != null) {
            customer.setBalance(customer.getBalance() + amount);
            customers.put(id, customer);
            return customer;
        }
        return null;
    }

    public boolean deleteCustomer(Long id) {
        if (customers.containsKey(id)) {
            customers.remove(id);
            return true;
        }
        return false;
    }
}