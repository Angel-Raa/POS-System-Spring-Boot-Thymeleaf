/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.service;

/**
 *
 * @author aguero
 */
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.angel.dto.CustomerDTO;

public interface CustomerService {
    List<CustomerDTO> findAll();
    Page<CustomerDTO> findAllDtosPages(Pageable pageable);

    CustomerDTO findById(Long theId);

    void save(CustomerDTO customerDTO);

    void update(Long theId, CustomerDTO customerDTO);

    void delete(Long theId);

    List<CustomerDTO> searchByName(String theName);

    boolean existsByEmail(String email);

    CustomerDTO findByEmail(String email);

    List<CustomerDTO> findDistinctByLastnameAndFirstname(String firstName, String lastName);

}
