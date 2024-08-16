/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import java.util.List;

import com.github.angel.exception.EmailAlreadyExistsException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.angel.dto.CustomerDTO;
import com.github.angel.entity.Customer;
import com.github.angel.repository.CustomerRepository;
import com.github.angel.service.CustomerService;
import com.github.angel.exception.ResourceNotFoundException;

/**
 *
 * @author aguero
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl( CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAllDto();
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findById(Long theId) {
        return customerRepository.findByIdDto(theId).orElseThrow(() -> new ResourceNotFoundException("Client not found with that ID " + theId));
    }

    @Transactional
    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = mapToCustomer(customerDTO);
        String email = customer.getEmail();
        if (customerRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("There is already a client with this email." + email);
        }
        customerRepository.persist(customer);

    }

    @Transactional
    @Override
    public void update(Long theId, CustomerDTO customerDTO) {
        if (customerRepository.existsById(theId)) {
            Customer customer = mapToCustomer(customerDTO);
            customer.setId(theId);
            customerRepository.update(customer);
        }
        else {
            throw new ResourceNotFoundException("Client not found with that ID " + theId);
        }
    }

    @Transactional
    @Override
    public void delete(Long theId) {
        if(customerRepository.existsById(theId)){
            customerRepository.deleteById(theId);
        }
        throw new ResourceNotFoundException("Client not found with that ID " + theId);

    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> searchByName(String theName) {
        return customerRepository.findByFirstName(theName);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public CustomerDTO findByEmail(String email) {
        return  customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Client not found with that email " + email));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findDistinctByLastnameAndFirstname(String firstName, String lastName) {
        return customerRepository.findByLastnameAndFirstname(firstName, lastName);
    }

    private static @NotNull Customer mapToCustomer(@NotNull CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setAddress(customerDTO.getAddress());
        customer.setEmail(customerDTO.getEmail());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setTel(customerDTO.getTel());
        return customer;
    }

    @Contract("_ -> new")
    private static @NotNull CustomerDTO mapToCustomerDTO(@NotNull Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getId());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setTel(customer.getTel());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setFirstName(customer.getFirstName());
        return customerDTO;
    }

}
