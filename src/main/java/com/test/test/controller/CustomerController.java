package com.test.test.controller;

import com.test.test.model.Customer;
import com.test.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCu() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


    @GetMapping("/name/{name}")
    public ResponseEntity<List<Customer>> getCuByName(@PathVariable String name) {
        return ResponseEntity.ok(customerService.getCustomersByName(name));
    }

}
