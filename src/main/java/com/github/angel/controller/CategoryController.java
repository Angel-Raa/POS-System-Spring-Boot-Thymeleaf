/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.entity.Category;
import com.github.angel.service.CategoryService;
import com.github.angel.utils.PageRenderUtils;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('WRITE')")
    public String getCategory(final Model model) {
        model.addAttribute("category", new Category());
        return "category/add-category";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('WRITE')")
    public String createCategory(@Valid final CategoryDTO category, final BindingResult result, final Model model,
            final RedirectAttributes attributes) {
        if (result.hasErrors()) {
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("category", category);
            model.addAttribute("errors", errores);
            return "category/add-category";

        }
        if (service.existsByName(category.getName())) {
            attributes.addAttribute("message", "The category already exists");
            return "category/add-category";
        }
        model.addAttribute("category", category);
        service.createCategory(category);
        attributes.addFlashAttribute("message", "The category has been created successfully");
        return "redirect:/category/list";
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('READ')")
    public String getListCategory(@RequestParam(name = "pages", defaultValue = "0") int pages, final Model model) {
        Pageable pageRequest = PageRequest.of(pages, 5);
        Page<CategoryDTO> categories = service.getAllCategories(pageRequest);
        PageRenderUtils<CategoryDTO> pageableUtils = new PageRenderUtils<>("/category/list", categories);
        model.addAttribute("pageRenderUtils", pageableUtils);
        model.addAttribute("categories", categories);
        return "category/list-category";
    }

    @GetMapping("/edit/{categoryId}")
    public String updatecategory(@PathVariable @Min(1) Long categoryId,
            final Model model) {
        CategoryDTO category = service.getCategoryById(categoryId);
        model.addAttribute("category", category);
        return "category/edit-category";
    }

    @PostMapping("/edit/{categoryId}")
    public String updateCategoryById(
            @Valid CategoryDTO category, final BindingResult result,
            final Model model, final RedirectAttributes attributes,
            @PathVariable @Min(1) Long categoryId) {
        if (result.hasErrors()) {
            Map<String, Object> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage));
            model.addAttribute("category", category);
            model.addAttribute("errors", errores);
            return "category/edit-category";

        }
        service.updateCategory(categoryId, category);
        attributes.addFlashAttribute("message", "The category has been updated successfully");

        return "redirect:/category/list";
    }

    @GetMapping("/delete/{categoryId}")
    @PreAuthorize("hasAuthority('DELETE')")
    public String deleteCategory(@PathVariable @Min(1) Long categoryId, final RedirectAttributes attributes) {
        service.deleteCategoryById(categoryId);
        attributes.addFlashAttribute("message", "The category has been deleted successfully");
        return "redirect:/category/list";
    }

}
