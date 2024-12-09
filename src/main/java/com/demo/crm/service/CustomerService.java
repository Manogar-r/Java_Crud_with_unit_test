package com.demo.crm.service;

import com.demo.crm.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerService {

    String saveCustomerDetail(Customer customer) throws Exception;
    Customer getCustomerById(long customerId) throws Exception;
    List<Customer> getAllCustomers();
    String deleteCustomerById(long customerId);
    String updateCustomerDetail(long customerId, Customer customer) throws Exception;
}
