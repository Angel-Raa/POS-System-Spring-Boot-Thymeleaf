/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.angel.dto.CustomerDTO;
import com.github.angel.service.CustomerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.validation.FieldError;
import java.util.stream.Collectors;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String getCustomer(final Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "customer/add-customer";
    }

    @PostMapping
    public String createCustomer(@Valid final CustomerDTO customer, final BindingResult result, final Model model,
            final RedirectAttributes attributes) {
        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("customer", customer);
            System.out.println(customer.toString());
            return "customer/add-customer"; 
        }

        System.out.println(customer.toString());
        model.addAttribute("customer", customer);
        customerService.save(customer);
        // Añade un mensaje de éxito y redirige a la lista de clientes
        attributes.addFlashAttribute("message", "Customer added successfully!");
        return "redirect:/customer/list"; 
    }

    @GetMapping("/list")
    public String listCustomers(final Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/list-customer";
    }
}
