package com.bookstore.secured.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers_secured")
public class CustomerSecured {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Length(min = 1, max = 50, message = "First name should have at least 1 character and at most 50 characters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Length(min = 1, max = 50, message = "Last name should have at least 1 character and at most 50 characters")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookSecured bookSecured;

    @PastOrPresent(message = "Start date should be in the past or present")
    private LocalDate startDate;

    @Future(message = "End date should be in the future")
    private LocalDate endDate;
}

