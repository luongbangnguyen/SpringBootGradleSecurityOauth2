package com.asiatech.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asiatech.spring.entity.Customer;
import com.asiatech.spring.service.CustomerService;

@Controller
class IndexController {

    @Autowired
    private CustomerService cusSv;

    @RequestMapping("/index")
    public String getIndexPage(Customer customer, ModelMap model) {
        List<Customer> customers = cusSv.getListCustomer();
        model.put("customers", customers);
        return "index";
    }

    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute("customer") Customer cus,
                          RedirectAttributes redirectAttrs) {
        Customer cusSaved = cusSv.save(cus);
        redirectAttrs.addAttribute("name", cusSaved.getFirstName());
        return "redirect:/account";
    }
}
