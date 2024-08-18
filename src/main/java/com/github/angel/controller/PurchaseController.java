/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.angel.dto.ProductDTO;
import com.github.angel.dto.PurchaseDTO;
import com.github.angel.service.ProductService;
import com.github.angel.service.PurchaseService;
import com.github.angel.utils.PageRenderUtils;

import jakarta.validation.Valid;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    private final PurchaseService service;
    private final ProductService productService;

    
    public PurchaseController(PurchaseService service, ProductService productService) {
        this.service = service;
        this.productService = productService;
    }

    @ModelAttribute("paymentMethods")
    public List<String >paymentMethods(){
        return List.of("Credit Card", "PayPal", "Bank Transfer");
    }
    @ModelAttribute("products")
    public List<ProductDTO> products(){
        return productService.getProductsAllName();
    }

    @GetMapping
    public String getSave(final Model model) {
        model.addAttribute("purchase", new PurchaseDTO());
        return "purchase/add-purchase";
    }

    @PostMapping
    public String createPurchase(@Valid final PurchaseDTO purchase,
            final BindingResult result,
            final RedirectAttributes attributes,
            final Model model) {
        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("purchase", purchase);
            return "purchase/add-purchase";
        }

        model.addAttribute("purchase", purchase);
        attributes.addFlashAttribute("message", "Purchase added successfully!");
        service.createPurchase(purchase);
        return "redirect:/purchase/list";
    }

    @GetMapping("/list")
    public String getListPurchase(@RequestParam(name = "pages", defaultValue = "0") int pages, final Model model) {
        Pageable pageable = PageRequest.of(pages, 5);
        Page<PurchaseDTO> purchases = service.getAllPurchases(pageable);
        PageRenderUtils<PurchaseDTO> pageRender = new PageRenderUtils<>("/purchase/list", purchases);
        model.addAttribute("pageRenderUtils", pageRender);
        model.addAttribute("purchases", purchases);
        return "purchase/list-purchase";
    }

  
}
