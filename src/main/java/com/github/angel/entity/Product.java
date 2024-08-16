/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.JdbcTypeCode;

/**
 *
 * @author aguero
 */
@Entity(name = "Product")
@Table(name = "products")
public class Product implements Serializable {
    @Serial
    private static final long serialVersionUID = 1038171071625172134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;
    @Column(name =" category_id", nullable = false, insertable = true, updatable = true)
    private Long categoryId;

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Name must be at most 40 characters long")
    @Column(length = 40)
    private String name;

    @Positive(message = "Price must be greater than zero")
    @NotNull(message = "Price is required")
    @Digits(integer = 6, fraction = 2, message = "Price should be a valid decimal number with up to 6 digits and 2 decimal places")
    @Column(precision = 6, scale = 2)
    private BigDecimal price;

    @PositiveOrZero(message = "Stock must be zero or positive")
    @NotNull(message = "Stock is required")
    private Integer stock;

    @NotBlank(message = "Description is required")
    @Size(max = 200, message = "Description must be at most 200 characters long")
    @Column(length = 200)
    @Lob
    @JdbcTypeCode(java.sql.Types.LONGVARCHAR)
    private String description;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id", insertable = false,  updatable = false)
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, Integer stock, String description, Category category, BigDecimal price) {
        this.productId = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.category = category;
        this.price = price;
    }

   

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product [id=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock + ", description="
                + description + "]";
    }

}
