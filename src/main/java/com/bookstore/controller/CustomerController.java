package com.bookstore.controller;

import com.bookstore.secured.model.CustomerSecured;
import com.bookstore.secured.service.CustomerServiceSecured;
import com.bookstore.unsecured.model.Customer;
import com.bookstore.unsecured.service.CustomerServiceUnsecured;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerServiceUnsecured customerServiceUnsecured;

    private final CustomerServiceSecured customerServiceSecured;

    @PostMapping("/unsecured")
    public ResponseEntity<String> addCustomerUnsecured(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerServiceUnsecured.addCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/unsecured/borrows")
    public List<Customer> getCustomerBorrowsByEmailUnsecured(@RequestParam String email) {
        return customerServiceUnsecured.getCustomerBorrowsByEmail(email);
    }

    @PostMapping("/secured")
    public ResponseEntity<String> addCustomerSecured(@Valid @RequestBody CustomerSecured customerSecured) {
        return new ResponseEntity<>(customerServiceSecured.addCustomer(customerSecured), HttpStatus.OK);
    }

    @GetMapping("/secured/borrows")
    public List<CustomerSecured> getCustomerBorrowsByEmailSecured(@Email @RequestParam String email) {
        return customerServiceSecured.getCustomerBorrowsByEmail(email);
    }
}

