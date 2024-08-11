/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import java.util.List;

import com.github.angel.exception.EmailAlreadyExistsException;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
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

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll().stream().map(CustomerServiceImpl::mapToCustomerDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDTO findById(Long theId) {
        return mapToCustomerDTO(customerRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Clients not found with that ID " + theId)));
    }

    @Transactional
    @Override
    public void save(CustomerDTO customerDTO) {
        Customer customer = mapToCustomer(customerDTO);
        String email = customer.getEmail();
        if (customerRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException("There is already a client with this email." + email);
        }
        customerRepository.save(customer);

    }

    @Transactional
    @Override
    public void update(Long theId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Clients not found with that ID " + theId));
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setTel(customerDTO.getTel());
        customer.setAddress(customerDTO.getAddress());
        customerRepository.save(customer);

    }

    @Transactional
    @Override
    public void delete(Long theId) {
        Customer customer = customerRepository.findById(theId)
                .orElseThrow(() -> new ResourceNotFoundException("Clients not found with that ID " + theId));
        customerRepository.delete(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> searchByName(String theName) {
        return customerRepository.findByFirstNameIgnoreCase(theName).stream()
                .map(CustomerServiceImpl::mapToCustomerDTO).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    @Override
    public CustomerDTO findByEmail(String email) {
        return mapToCustomerDTO(customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("A client with that email has not been found" + email)));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerDTO> findDistinctByLastnameAndFirstname(String firstName, String lastName) {
        return customerRepository.findByLastnameAndFirstname(firstName, lastName).stream()
                .map(CustomerServiceImpl::mapToCustomerDTO).toList();
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
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getTel(),
                customer.getAddress());
    }

}
