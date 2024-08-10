package com.github.angel.service;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id, CategoryDTO dto);
    List<CategoryDTO>getAllCategories ();
    CategoryDTO getCategoryById(Long id);
    void deleteCategoryById(Long id);

    List<ProductDTO> getProductsByCategory(Long categoryId);
    void moveProductToCategory(Long productId, Long newCategoryId);


}
