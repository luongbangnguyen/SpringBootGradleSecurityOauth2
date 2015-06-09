package com.asiatech.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asiatech.spring.entity.Customer;

public interface CustomerRespository extends JpaRepository<Customer, Integer> {
    /**
     * get customer by fist name
     *
     * @param name
     * @return
     */
    Customer findByFirstName(String name);

    /**
     * get customer by user name used in security
     *
     * @param userName
     * @return
     */
    Customer findByUserName(String userName);
}
