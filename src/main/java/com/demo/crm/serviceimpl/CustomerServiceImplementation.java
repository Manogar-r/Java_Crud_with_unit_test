package com.demo.crm.serviceimpl;

import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import com.demo.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;


    @Override
    public String saveCustomerDetail(Customer customer) throws Exception {

        try {
            Customer newCustomer = Customer.builder()
                    .customerName(customer.getCustomerName())
                    .customerMail(customer.getCustomerMail())
                    .phoneNumberOne(customer.getPhoneNumberOne())
                    .phoneNumberTwo(customer.getPhoneNumberTwo())
                    .customerAddress(customer.getCustomerAddress())
                    .DOB(customer.getDOB())
                    .build();

            customerRepository.save(newCustomer);

        } catch (Exception e) {
            throw new Exception("Something went Wrong!!");
        }

        return "Customer Data Saved Successfully!";
    }

    @Override
    public Customer getCustomerById(long customerId) throws Exception {
        return customerRepository.findById(customerId).orElseThrow(() -> new Exception("User not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public String deleteCustomerById(long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return "Customer Deleted Successfully!";
        } else {
            return "Customer not found!";
        }
    }

    @Override
    public String updateCustomerDetail(long customerId, Customer customer) throws Exception {
        try {
            Optional<Customer> existingCustomer = customerRepository.findById(customerId);
            if (existingCustomer.isPresent()) {
                Customer updatedCustomer = getCustomer(customer, existingCustomer);
                customerRepository.save(updatedCustomer);
                return "Customer Updated Successfully!";
            } else {
                return "Customer not found!";
            }
        } catch (Exception e) {
            throw new Exception("Something went wrong while updating customer data.");
        }
    }

    private static Customer getCustomer(Customer customer, Optional<Customer> existingCustomer) {
        Customer updatedCustomer = existingCustomer.get();
        updatedCustomer.setCustomerName(customer.getCustomerName());
        updatedCustomer.setCustomerMail(customer.getCustomerMail());
        updatedCustomer.setPhoneNumberOne(customer.getPhoneNumberOne());
        updatedCustomer.setPhoneNumberTwo(customer.getPhoneNumberTwo());
        updatedCustomer.setCustomerAddress(customer.getCustomerAddress());
        updatedCustomer.setDOB(customer.getDOB());
        return updatedCustomer;
    }
}
