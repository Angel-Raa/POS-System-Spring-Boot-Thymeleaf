package com.github.angel.service.impl;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;
import com.github.angel.repository.CategoryRepository;
import com.github.angel.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO dto) {
        return null;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDTO getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {
        // TODO document why this method is empty
    }

    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public void moveProductToCategory(Long productId, Long newCategoryId) {

    }


}
