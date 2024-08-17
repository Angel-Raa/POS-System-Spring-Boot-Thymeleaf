package com.github.angel.service;

import com.github.angel.dto.CategoryDTO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(Long id, CategoryDTO dto);
    List<CategoryDTO>getAllCategories ();
    Page<CategoryDTO> getAllCategories(Pageable pageable);
    List<CategoryDTO> getAllCategoriesName();
    CategoryDTO getCategoryById(Long id);
    void deleteCategoryById(Long id);
    boolean existsByName(String name);
    void moveProductToCategory(Long productId, Long newCategoryId);


}
