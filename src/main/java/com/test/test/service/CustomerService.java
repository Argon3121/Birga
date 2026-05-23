package com.test.test.service;

import com.test.test.model.Customer;
import com.test.test.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getCustomersByName(String name) {
        return customerRepository.findByName(name);
    }

    public Customer createCustomer(Customer customer) {
        customer.setBalance(0.0);
        return customerRepository.save(customer);
    }

    public Customer updateBalance(Long id, double amount) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setBalance(customer.getBalance() + amount);
            return customerRepository.save(customer);
        }
        return null;
    }

    public boolean deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }
}