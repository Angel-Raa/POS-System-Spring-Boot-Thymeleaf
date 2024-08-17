package com.github.angel.service.impl;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.entity.Category;
import com.github.angel.exception.ResourceAlreadyExistsException;
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
        String name = category.getName();
        if (categoryRepository.existsByName(name)) {
            throw new ResourceAlreadyExistsException("Category with name " + name + " already exists");
        }
        categoryRepository.persist(category);
    }

    @Transactional
    @Override
    public void updateCategory(Long id, CategoryDTO dto) {
        if (categoryRepository.existsById(id)) {
            Category category = matToCategory(dto);
            category.setCategoryId(id);
            categoryRepository.update(category);
        } else {
            throw new ResourceNotFoundException("No category found with that Id" + id);
        }

    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAllDtos();
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryDTO getCategoryById(Long id) {
        return categoryRepository.findByIdDto(id)
                .orElseThrow(() -> new ResourceNotFoundException("No category found with that Id" + id));
    }

    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No category found with that Id" + id);
        }
    }

    @Transactional
    @Override
    public void moveProductToCategory(Long productId, Long newCategoryId) {
    }

    @Contract("_ -> new")
    private static @NotNull CategoryDTO mapToCategoryDto(@NotNull Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getName(),
                category.getDescription());
    }

    @Contract(pure = true)
    private static @NotNull Category matToCategory(@NotNull CategoryDTO dto) {
        Category category = new Category();
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setCategoryId(dto.getCategoryId());
        return category;
    }


    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<CategoryDTO> getAllCategoriesName() {
        return categoryRepository.findAllNameDtos();
    }

}
