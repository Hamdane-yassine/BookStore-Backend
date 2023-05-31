package com.bookstore.unsecured.dao;

import com.bookstore.unsecured.model.Book;
import com.bookstore.unsecured.model.Customer;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CustomersManager {

    // DataSource to interact with the database
    private final DataSource dataSource;

    // Constructor for the class, injects the DataSource
    public CustomersManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Customer> getCustomerBorrowsByEmail(String email) {
        String sql = "SELECT customers.*, books.name as book_name FROM customers "
                + "LEFT JOIN books ON customers.book_id = books.id "
                + "WHERE customers.email = '" + email + "'";

        return getCustomers(sql);
    }

    // Private method to create a list of customers based on the SQL query passed to it
    private List<Customer> getCustomers(String sql) {
        // Establish a connection to the database
        Connection c = null;
        ResultSet rs = null;
        // Execute the query
        try {
            c = dataSource.getConnection();
            rs = c.createStatement().executeQuery(sql);

            List<Customer> customers = new ArrayList<>();
            // Iterate over each entry in the ResultSet
            while (rs.next()) {
                Customer customer = new Customer();
                // Extract the data from each column for this entry and set it on the Customer object
                customer.setId(rs.getLong("id"));
                customer.setFirstName(rs.getString("first_name"));
                customer.setLastName(rs.getString("last_name"));
                customer.setEmail(rs.getString("email"));
                customer.setStartDate(rs.getDate("start_date").toLocalDate());
                customer.setEndDate(rs.getDate("end_date").toLocalDate());
                Book book = new Book();
                book.setName(rs.getString("book_name"));
                customer.setBook(book);
                // Add the customer to the list
                customers.add(customer);
            }
            return customers;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to add a new customer to the database
    public String addCustomer(Customer customer) {
        // Establish a connection to the database

        Connection c = null;
        try {
            c = dataSource.getConnection();

            String sqlBook = "INSERT INTO books (name) VALUES ('"
                    + customer.getBook().getName() + "') RETURNING id";

            ResultSet rs = c.createStatement().executeQuery(sqlBook);
            rs.next();
            long bookId = rs.getLong("id");


            // Add the customer with the book's ID
            String sqlCustomer = "INSERT INTO customers (first_name,last_name,email, start_date, end_date, book_id) VALUES ('"
                    + customer.getFirstName() + "', '"
                    + customer.getLastName() + "', '"
                    + customer.getEmail() + "', '"
                    + customer.getStartDate() + "', '"
                    + customer.getEndDate() + "', "
                    + bookId + ") RETURNING id";

            c.createStatement().executeQuery(sqlCustomer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Your request to borrow the book has been submitted!";
    }

}



