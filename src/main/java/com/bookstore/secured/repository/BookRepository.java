package com.bookstore.secured.repository;

import com.bookstore.secured.model.BookSecured;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookSecured, Long> {
    BookSecured findByName(String name);
}
