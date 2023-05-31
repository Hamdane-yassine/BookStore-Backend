package com.bookstore.unsecured.service;

import com.bookstore.unsecured.dao.CustomersManager;
import com.bookstore.unsecured.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceUnsecured {

    private final CustomersManager customersManager;

    @Autowired
    public CustomerServiceUnsecured(CustomersManager customersManager) {
        this.customersManager = customersManager;
    }


    public String addCustomer(Customer customer) {
         return customersManager.addCustomer(customer);
    }

    public List<Customer> getCustomerBorrowsByEmail(String email) {
        return customersManager.getCustomerBorrowsByEmail(email);
    }
}

