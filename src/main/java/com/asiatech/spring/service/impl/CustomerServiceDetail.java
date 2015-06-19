package com.asiatech.spring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.asiatech.spring.enmum.Roles;
import com.asiatech.spring.entity.Customer;
import com.asiatech.spring.repository.CustomerRepository;

@Service
public class CustomerServiceDetail implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRespository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isBlank(username)) {
            throw new UsernameNotFoundException("User name is not empty");
        }
        Customer customer = customerRespository.findByUserName(username);
        Roles role = customer.getRole();
        User user = new User(customer.getUserName(), customer.getPassword(), getGrantedAuthorities(role));
        return user;
    }

    private static List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roles.name()));
        return authorities;
    }

}
