/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.angel.dto.CustomerDTO;
import com.github.angel.entity.Customer;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.domain.Pageable;


/**
 *
 * @author aguero
 */
@Repository
public interface CustomerRepository extends ListPagingAndSortingRepository<Customer, Long>, BaseJpaRepository<Customer, Long>{
    boolean existsByEmail(String email);
    @Query("SELECT com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c WHERE c.email = :email")
    Optional<CustomerDTO> findByEmail(@Param("email") String email);

    @Query("SELECT com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c WHERE LOWER(c.firstName) = LOWER(:firstName)")
    List<CustomerDTO> findByFirstName(@Param("firstName") String firstName);

    @Query("SELECT com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName")
    List<CustomerDTO> findByLastnameAndFirstname(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT new com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c")
    List<CustomerDTO> findAllDto();

    @Query("SELECT new com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c WHERE c.customerId = :customerId")
    Optional<CustomerDTO> findByIdDto(@Param("customerId") Long customerId);


    @Query(value =  "SELECT new com.github.angel.dto.CustomerDTO(c.customerId, c.firstName, c.lastName, c.email, c.tel, c.address) FROM Customer c",
    countQuery = "SELECT COUNT(c.customerId) FROM Customer c")
    Page<CustomerDTO> findAllDtoPages(Pageable pageable);



}
