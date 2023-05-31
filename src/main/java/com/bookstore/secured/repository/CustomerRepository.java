package com.bookstore.secured.repository;

import com.bookstore.secured.model.CustomerSecured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerSecured, Long> {
    @Query("SELECT c FROM CustomerSecured c LEFT JOIN c.bookSecured b WHERE c.email = :email")
    List<CustomerSecured> findCustomersWithBooksByEmail(@Param("email") String email);
}
