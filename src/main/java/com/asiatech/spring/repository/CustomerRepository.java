package com.asiatech.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.asiatech.spring.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>,
JpaSpecificationExecutor<Customer> {
    /**
     * get customer by fist name
     *
     * @param name
     * @return
     */
    Customer findByFistname(String name);

    /**
     * get customer by user name used in security
     *
     * @param userName
     * @return
     */
    Customer findByUserName(String userName);
}
