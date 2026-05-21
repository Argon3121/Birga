package com.test.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {

        private String name;
        private String familia;
        private String otchestvo;
        private String mail;
        private double balance;

}
