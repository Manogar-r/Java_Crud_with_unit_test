package com.demo.crm.serviceimpl;

import com.demo.crm.entity.Customer;
import com.demo.crm.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplementationTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImplementation customerService;

    private Customer mockCustomer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock Customer object
        mockCustomer = Customer.builder()
                .customerName("John Doe")
                .customerMail("johndoe@example.com")
                .phoneNumberOne(1234567890)
                .customerAddress("123 Elm Street")
                .DOB(LocalDate.parse("1990-01-01"))
                .build();
    }

    @Test
    void saveCustomerDetail() {
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        Assertions.assertDoesNotThrow(() -> customerService.saveCustomerDetail(mockCustomer));

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void getCustomerById_Success() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));

        Customer result = customerService.getCustomerById(1L);

        assertNotNull(result);
        assertEquals(mockCustomer.getCustomerName(), result.getCustomerName());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getCustomerById_NotFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> customerService.getCustomerById(1L));

        assertEquals("User not found", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        customerList.add(mockCustomer);
        when(customerRepository.findAll()).thenReturn(customerList);

        List<Customer> result = customerService.getAllCustomers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockCustomer.getCustomerName(), result.get(0).getCustomerName());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    void deleteCustomerById_Success() {
        when(customerRepository.existsById(1L)).thenReturn(true);
        doNothing().when(customerRepository).deleteById(1L);

        String result = customerService.deleteCustomerById(1L);

        assertEquals("Customer Deleted Successfully!", result);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteCustomerById_NotFound() {
        when(customerRepository.existsById(1L)).thenReturn(false);

        String result = customerService.deleteCustomerById(1L);

        assertEquals("Customer not found!", result);
        verify(customerRepository, times(1)).existsById(1L);
        verify(customerRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void updateCustomerDetail_Success() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(mockCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(mockCustomer);

        Customer updatedCustomer = Customer.builder()
                .customerName("Jane Doe")
                .customerMail("janedoe@example.com")
                .build();

        String result = customerService.updateCustomerDetail(1L, updatedCustomer);

        assertEquals("Customer Updated Successfully!", result);
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void updateCustomerDetail_NotFound() throws Exception {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        String result = customerService.updateCustomerDetail(1L, mockCustomer);

        assertEquals("Customer not found!", result);
        verify(customerRepository, times(1)).findById(1L);
        verify(customerRepository, times(0)).save(any(Customer.class));
    }

    @Test
    void updateCustomerDetail_Exception() {
        when(customerRepository.findById(1L)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(Exception.class, () -> customerService.updateCustomerDetail(1L, mockCustomer));

        assertEquals("Something went wrong while updating customer data.", exception.getMessage());
        verify(customerRepository, times(1)).findById(1L);
    }
}
