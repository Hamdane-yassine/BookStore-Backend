package com.bookstore.controller;

import com.bookstore.secured.model.CustomerSecured;
import com.bookstore.secured.service.CustomerServiceSecured;
import com.bookstore.unsecured.model.Customer;
import com.bookstore.unsecured.service.CustomerServiceUnsecured;
import jakarta.validation.Valid;
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

    @PostMapping("/unsecured/borrows")
    public List<Customer> getCustomerBorrowsByEmailUnsecured(@RequestBody com.bookstore.unsecured.dto.BorrowsRequest borrowsRequest) {
        return customerServiceUnsecured.getCustomerBorrowsByEmail(borrowsRequest.getEmail());
    }

    @PostMapping("/secured")
    public ResponseEntity<String> addCustomerSecured(@Valid @RequestBody CustomerSecured customerSecured) {
        return new ResponseEntity<>(customerServiceSecured.addCustomer(customerSecured), HttpStatus.OK);
    }

    @PostMapping("/secured/borrows")
    public List<CustomerSecured> getCustomerBorrowsByEmailSecured(@RequestBody com.bookstore.secured.dto.BorrowsRequest borrowsRequest) {
        return customerServiceSecured.getCustomerBorrowsByEmail(borrowsRequest.getEmail());
    }
}

