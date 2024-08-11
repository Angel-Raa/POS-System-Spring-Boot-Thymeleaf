package com.github.angel.service.impl;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;
import com.github.angel.entity.Category;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.CategoryRepository;
import com.github.angel.service.CategoryService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    @Transactional
    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = matToCategory(categoryDTO);
        categoryRepository.save(category);
    }
    @Transactional
    @Override
    public void updateCategory(Long id, CategoryDTO dto) {


    }
    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryServiceImpl::mapToCategoryDto)
                .toList();
    }
    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getCategoryById(Long id) {
        return mapToCategoryDto(categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No category found with that Id" + id)));
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


    @Contract("_ -> new")
    private static @NotNull CategoryDTO mapToCategoryDto(@NotNull Category category){
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription()
        );
    }

    @Contract(pure = true)
    private static @NotNull Category matToCategory(@NotNull CategoryDTO dto){
        Category category =  new Category();
        category.setName(dto.name());
        category.setDescription(dto.description());
        category.setCategoryId(dto.categoryId());
        return category;
    }

}
