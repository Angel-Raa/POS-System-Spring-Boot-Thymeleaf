/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.github.angel.dto.CustomerDTO;
import com.github.angel.entity.Customer;

/**
 *
 * @author aguero
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{
    boolean existsByEmail(String email);
    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findByEmail(@Param("email") String email);
    List<Customer> findByFirstNameIgnoreCase(String firstName);
    @Query("SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName")
    List<Customer> findByLastnameAndFirstname(@Param("firstName") String firstName, @Param("lastName") String lastName);
    @Query("SELECT new com.github.angel.dto.CustomerDTO(c.id, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c")
    List<CustomerDTO> findAllDtos();



}
