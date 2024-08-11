package com.github.angel.service.impl;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.dto.ProductDTO;
import com.github.angel.entity.Category;
import com.github.angel.entity.Product;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.CategoryRepository;
import com.github.angel.repository.ProductRepository;
import com.github.angel.service.CategoryService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
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
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No category found with that Id" + id));
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        categoryRepository.save(category);


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
    @Transactional
    @Override
    public void deleteCategoryById(Long id) {
      CategoryDTO categoryDTO = getCategoryById(id);
      categoryRepository.deleteById(categoryDTO.getCategoryId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return categoryRepository.findByProductsByCategory(categoryId).stream()
        .map(CategoryServiceImpl::mapToproductDTO)
        .toList();
    }

    @Transactional
    @Override
    public void moveProductToCategory(Long productId, Long newCategoryId) {
        Product newProduct = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("No product found with that Id" + productId));
        Category newCategory = categoryRepository.findById(newCategoryId).orElseThrow(() -> new ResourceNotFoundException("No category found with that Id" + newCategoryId));
        newProduct.setCategoryId(newCategory);
        productRepository.save(newProduct);


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
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setCategoryId(dto.getCategoryId());
        return category;
    }

    @Contract(pure = true)
    private static @NotNull ProductDTO mapToproductDTO(Product product){
        return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getStock(),
            product.getDescription(),
            product.getCategoryId().getCategoryId()

        );
    }

}
