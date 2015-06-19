package com.asiatech.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.asiatech.spring.entity.Customer;

public interface CustomerService {
    public static String NAME = "customerService";

    Customer findByFirstName(String name);

    Customer save(Customer customer);

    List<Customer> getListCustomer();
    
    Page<Customer> getCustomers(String fistname, String lastname,Integer pagenumber);
}
