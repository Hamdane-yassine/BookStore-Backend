package com.bookstore.secured.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class BorrowsRequest {

    @Email
    private String email;
}
