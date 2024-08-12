package com.github.angel.repository;

import com.github.angel.entity.Category;
import com.github.angel.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId ")
    List<Product> findByProductsByCategory(@Param("categoryId") Long categoryId);
    
    boolean existsByName(String name);
}
