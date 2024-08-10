/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */

package com.github.angel.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 *
 * @author aguero
 */
public record CustomerDTO(
        Long customerId,
        @NotBlank(message = "First name is required")
        @Size(max = 40, message = "First name must not exceed 40 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 40, message = "Last name must not exceed 40 characters")
        String lastName,

        @NotBlank(message = "Telephone number is required")
        @Pattern(regexp = "\\d{10}", message = "Telephone number must be 10 digits")
        String tel,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 40, message = "Email must not exceed 40 characters")
        String email,

        @NotBlank(message = "Address is required")
        @Size(max = 80, message = "Address must not exceed 80 characters")
        String address) {

}
