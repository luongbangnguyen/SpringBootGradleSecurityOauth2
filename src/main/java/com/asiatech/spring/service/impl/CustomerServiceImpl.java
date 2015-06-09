package com.asiatech.spring.service.impl;


import com.asiatech.spring.enmum.Roles;
import com.asiatech.spring.entity.Customer;
import com.asiatech.spring.repository.CustomerRespository;
import com.asiatech.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRespository customerRespository;
    
    @Autowired 
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Customer findByFirstName(String name) {
        return customerRespository.findByFirstName(name);
    }


    @Override
    @Transactional
    public Customer save(Customer customer) {
    	Customer cus = customerRespository.findByUserName(customer.getUserName());
    	if(cus != null && cus.getId() != 0){
    		throw new IllegalArgumentException("user name exits");
    	}
        String passWordEncoder = bCryptPasswordEncoder.encode(customer.getPassword());
        customer.setPassword(passWordEncoder);
        customer.setRole(Roles.ROLE_ADMIN);
        return customerRespository.save(customer);
    }

    @Override
    public List<Customer> getListCustomer() {
        return customerRespository.findAll();
    }

}
