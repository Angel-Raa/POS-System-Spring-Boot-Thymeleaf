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
public class CustomerDTO {
        Long customerId;
        @NotBlank(message = "First name is required")
        @Size(max = 40, message = "First name must not exceed 40 characters")
        private String firstName;

        @NotBlank(message = "Last name is required")
        @Size(max = 40, message = "Last name must not exceed 40 characters")
        private String lastName;

        @NotBlank(message = "Telephone number is required")
        @Pattern(regexp = "\\d{10}", message = "Telephone number must be 10 digits")
        private String tel;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @Size(max = 40, message = "Email must not exceed 40 characters")
        private String email;

        @NotBlank(message = "Address is required")
        @Size(max = 80, message = "Address must not exceed 80 characters")
        private String address;

        public CustomerDTO() {
        }

        public CustomerDTO(Long customerId,
                        @NotBlank(message = "First name is required") @Size(max = 40, message = "First name must not exceed 40 characters") String firstName,
                        @NotBlank(message = "Last name is required") @Size(max = 40, message = "Last name must not exceed 40 characters") String lastName,
                        @NotBlank(message = "Telephone number is required") @Pattern(regexp = "\\d{12}", message = "Telephone number must be 12 digits") String tel,
                        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") @Size(max = 40, message = "Email must not exceed 40 characters") String email,
                        @NotBlank(message = "Address is required") @Size(max = 80, message = "Address must not exceed 80 characters") String address) {
                this.customerId = customerId;
                this.firstName = firstName;
                this.lastName = lastName;
                this.tel = tel;
                this.email = email;
                this.address = address;
        }

        public Long getCustomerId() {
                return customerId;
        }

        public String getFirstName() {
                return firstName;
        }

        public String getLastName() {
                return lastName;
        }

        public String getTel() {
                return tel;
        }

        public String getEmail() {
                return email;
        }

        public String getAddress() {
                return address;
        }

        @Override
        public String toString() {
                return "CustomerDTO [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
                                + ", tel=" + tel + ", email=" + email + ", address=" + address + "]";
        }

}
