package com.github.angel.repository;

import com.github.angel.dto.CategoryDTO;
import com.github.angel.entity.Category;
import com.github.angel.entity.Product;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends ListPagingAndSortingRepository<Category, Long>, BaseJpaRepository<Category, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.categoryId = :categoryId ")
    List<Product> findByProductsByCategory(@Param("categoryId") Long categoryId);
    
    boolean existsByName(String name);
    @Query("SELECT new com.github.angel.dto.CategoryDTO(c.categoryId, c.name, c.description) FROM Category c")
    List<CategoryDTO> findAllDtos();

    @Query("SELECT new com.github.angel.dto.CategoryDTO(c.categoryId, c.name) FROM Category c")
    List<CategoryDTO> findAllNameDtos();
    @Query("SELECT new com.github.angel.dto.CategoryDTO(c.categoryId, c.name, c.description) FROM Category c WHERE c.categoryId = :categoryId")
    Optional<CategoryDTO> findByIdDto(@Param("categoryId") Long categoryId);
    @Query(value = "SELECT new com.github.angel.dto.CategoryDTO(c.categoryId, c.name, c.description) FROM Category c",
    countQuery = "SELECT COUNT(c.categoryId) FROM Category c")
    Page<CategoryDTO> findAllDtosPages(Pageable pageable);
}
