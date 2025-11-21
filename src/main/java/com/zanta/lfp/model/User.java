package com.zanta.lfp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    @NotBlank(message = "First name is required")
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(nullable = false, length = 100, unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @Column(nullable = false)
    boolean gender;

    @Column(name = "join_date", nullable = false)
    private java.time.LocalDateTime joinDate = java.time.LocalDateTime.now();

    @Column(precision = 3, scale = 2)
    @DecimalMin(value = "0.00", message = "Rate must be >= 0.00")
    @DecimalMax(value = "5.00", message = "Rate must be <= 5.00")
    private BigDecimal rate = BigDecimal.valueOf(0.00);
}
