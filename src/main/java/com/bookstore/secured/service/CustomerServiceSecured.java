package com.bookstore.secured.service;

import com.bookstore.secured.model.BookSecured;
import com.bookstore.secured.model.CustomerSecured;
import com.bookstore.secured.repository.BookRepository;
import com.bookstore.secured.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceSecured {

    private final CustomerRepository customerRepository;
    private final BookRepository bookRepository;

    public CustomerServiceSecured(CustomerRepository customerRepository, BookRepository bookRepository) {
        this.customerRepository = customerRepository;
        this.bookRepository = bookRepository;
    }

    public List<CustomerSecured> getCustomerBorrowsByEmail(String email) {
        return customerRepository.findCustomersWithBooksByEmail(email);
    }

    @Transactional
    public String addCustomer(CustomerSecured customerSecured) {
        BookSecured bookSecured = new BookSecured();
        bookSecured.setName(customerSecured.getBookSecured().getName());
        customerSecured.setBookSecured(bookSecured);
        customerRepository.save(customerSecured);

        return "Your request to borrow the book has been submitted!";
    }

}

