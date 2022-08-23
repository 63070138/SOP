package com.example.lab3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {
    private List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<Customer>();
        customers.add(new Customer("1010", "John", "Male", 25));
        customers.add(new Customer("1080", "Peter", "Male", 24));
        customers.add(new Customer("1019", "Sara", "Female", 23));
        customers.add(new Customer("1110", "Rose", "Female", 23));
        customers.add(new Customer("1001", "Emma", "Female", 30));
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers() {
        return this.customers;
    }

    @RequestMapping(value = "/customerbyid/{id}", method = RequestMethod.GET)
    public List<Customer> getCustomerByID(@PathVariable("id") String ID) {
        for (:
             ) {
            
        }
    }
}

