package com.demo.crm.controller;

import com.demo.crm.service.CustomerService;
import com.demo.crm.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    public CustomerService customerService;

    // Save the customer data
    @PostMapping("/entry")
    public String saveCustomerDetail(@RequestBody Customer customer) throws Exception{
        return customerService.saveCustomerDetail(customer);
    }
    // Get the customer by ID
    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable long customerId) throws Exception {
        return customerService.getCustomerById(customerId);
    }

    // Get All Customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Delete Customer By ID
    @DeleteMapping("/{customerId}")
    public String deleteCustomerById(@PathVariable long customerId) {
        return customerService.deleteCustomerById(customerId);
    }

    // Update Customer Details
    @PutMapping("/{customerId}")
    public String updateCustomerDetail(@PathVariable long customerId, @RequestBody Customer customer) throws Exception {
        return customerService.updateCustomerDetail(customerId, customer);
    }

}