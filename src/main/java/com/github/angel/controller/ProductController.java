/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;
import com.github.angel.service.CategoryService;
import com.github.angel.service.ProductService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    private final CategoryService categoryService;
    private final ProductService service;

    public ProductController(ProductService service, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.service = service;
    }

    @GetMapping
    public String getCreateProduct(final Model model) {

        model.addAttribute("product", new ProductDTO());
        return "product/add-product";
    }

    @PostMapping
    public String postCreateProduct(@Valid ProductDTO product, final BindingResult result,
            final RedirectAttributes attributes, final Model model) {
        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("product", product);
            return "product/add-product";
        }
        service.createProduct(product);
        model.addAttribute("product", product);
        attributes.addFlashAttribute("message", "Product added successfully!");
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public String getAllProduct(final Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "product/list-product";
    }

    @GetMapping("/edit/{productId}")
    public String updateProduct(@PathVariable Long productId, final Model model) {
        ProductDTO product = service.getProductById(productId);
        model.addAttribute("product", product);
        return "product/edit-product";
    }

    @PostMapping("/edit/{productId}")
    public String postUpdateProduct(@Valid ProductDTO product, final BindingResult result,
            @PathVariable(name = "productId") @Min(1) Long productId, final RedirectAttributes attributes,
            final Model model) {

        if (result.hasErrors()) {
            // Recopila los errores de validación y los agrega al modelo
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("errors", errores);
            model.addAttribute("product", product);
            return "product/add-product";
        }
        service.updateProduct(product, productId);
        model.addAttribute("product", product);
        attributes.addFlashAttribute("message", "The product has been updated successfully.");

        return "redirect:/product/list";
    }

    
    @GetMapping("/delete/{productId}")
    public String deleteProduct(@PathVariable Long productId, final RedirectAttributes attributes) {
        service.deleteProduct(productId);
        attributes.addFlashAttribute("message", "The product has been deleted successfully.");
        return "redirect:/product/list";
    }


    @ModelAttribute("categories")
    public List<CategoryDTO> getCategories() {
        return categoryService.getAllCategoriesName();
    }

    @ModelAttribute("categoryEdit")
    public CategoryDTO getCategoryForEdit(@PathVariable(required = false) Long productId) {
        if (productId != null) {
            ProductDTO product = service.getProductById(productId);
            return categoryService.getCategoryById(product.getCategoryId());
        }
        return new CategoryDTO(); 
    }
}
