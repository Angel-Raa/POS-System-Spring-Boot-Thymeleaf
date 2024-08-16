/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.entity;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 *
 * @author aguero
 */
@Table(name = "customers", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Entity(name = "Customer")
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -1028172617161927111L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;
    @NotBlank(message = "First name field cannot be empty")
    @Size(max = 40, message = "First name must be at most 40 characters long")
    @Column(length = 40)
    private String firstName;
    @NotBlank(message = "Last name field cannot be empty")
    @Size(max = 40, message = "Last name must be at most 40 characters long")
    @Column(length = 40)
    private String lastName;
    @NotBlank(message = "Email field cannot be empty")
    @Size(max = 40, message = "Email must be at most 40 characters long")
    @Email(message = "Invalid email format")
    @Column(length = 40, unique = true)
    private String email;
    @NotBlank(message = "Tel field cannot be empty")
    @Size(max = 12, message = "Tel must be at most 12 characters long")
    @Column(length = 12)
    private String tel;
    @NotBlank(message = "Address field cannot be empty")
    @Size(max = 80, message = "Address must be at most 80 characters long")
    @Column(length = 80)
    private String address;

    public Customer() {
    }

    public Customer(Long id,
            @NotBlank(message = "First name field cannot be empty") @Size(max = 40, message = "First name must be at most 40 characters long") String firstName,
            @NotBlank(message = "Last name field cannot be empty") @Size(max = 40, message = "Last name must be at most 40 characters long") String lastName,
            @NotBlank(message = "Tel field cannot be empty") @Size(max = 40, message = "Tel must be at most 40 characters long") String tel,
            @NotBlank(message = "Email field cannot be empty") @Size(max = 40, message = "Email must be at most 40 characters long") @Email(message = "Invalid email format") String email,
            @NotBlank(message = "Address field cannot be empty") @Size(max = 80, message = "Address must be at most 80 characters long") String address) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return customerId;
    }

    public void setId(Long id) {
        this.customerId = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer [id=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", tel=" + tel
                + ", email=" + email + ", address=" + address + "]";
    }

}
