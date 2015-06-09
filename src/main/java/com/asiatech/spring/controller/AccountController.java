package com.asiatech.spring.controller;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asiatech.spring.entity.Customer;
import com.asiatech.spring.resource.CustomerResource;
import com.asiatech.spring.service.CustomerService;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    Mapper beanMapper;

    @Autowired
    CustomerService cusSv;

    @RequestMapping("")
    public String getPageAccount(@RequestParam(value = "name") String name, ModelMap model) {
        model.put("name", name);
        return "account";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public @ResponseBody CustomerResource createAccount(@RequestBody CustomerResource customerResource) {
        Customer cus = beanMapper.map(customerResource, Customer.class);
        Customer cusSaved = cusSv.save(cus);
        CustomerResource cusResource = beanMapper.map(cusSaved, CustomerResource.class);
        return cusResource;
    }
}
