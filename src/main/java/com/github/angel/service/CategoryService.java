package com.github.angel.service;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;

import java.util.List;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);
    void updateCategory(Long id, CategoryDTO dto);
    List<CategoryDTO>getAllCategories ();
    CategoryDTO getCategoryById(Long id);
    void deleteCategoryById(Long id);
    boolean existsByName(String name);
    List<ProductDTO> getProductsByCategory(Long categoryId);
    void moveProductToCategory(Long productId, Long newCategoryId);


}
