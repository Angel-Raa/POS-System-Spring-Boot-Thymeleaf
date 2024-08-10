/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 *
 * @author aguero
 */
@Entity
@Table(name = "products")
public class Product implements Serializable {
    private static final long serialVersionUID = 1038171071625172134L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @Size(max = 150, message = "Description must be at most 150 characters long")
    @Column(length = 150)
    private String description;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
    @JoinColumn(name = "category_id")
    private Category categoryId;

    public Product() {
    }

    public Product(Long id, String name, Integer stock, String description, Category categoryId, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.categoryId = categoryId;
        this.price = price;
    }

    public @Positive(message = "Price must be greater than zero") @NotNull(message = "Price is required") @Digits(integer = 6, fraction = 2, message = "Price should be a valid decimal number with up to 6 digits and 2 decimal places") BigDecimal getPrice() {
        return price;
    }

    public void setPrice(@Positive(message = "Price must be greater than zero") @NotNull(message = "Price is required") @Digits(integer = 6, fraction = 2, message = "Price should be a valid decimal number with up to 6 digits and 2 decimal places") BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Name is required") @Size(max = 40, message = "Name must be at most 40 characters long") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 40, message = "Name must be at most 40 characters long") String name) {
        this.name = name;
    }

    public @PositiveOrZero(message = "Stock must be zero or positive") @NotNull(message = "Stock is required") Integer getStock() {
        return stock;
    }

    public void setStock(@PositiveOrZero(message = "Stock must be zero or positive") @NotNull(message = "Stock is required") Integer stock) {
        this.stock = stock;
    }

    public @NotBlank(message = "Description is required") @Size(max = 150, message = "Description must be at most 150 characters long") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description is required") @Size(max = 150, message = "Description must be at most 150 characters long") String description) {
        this.description = description;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + ", description="
                + description + "]";
    }

}
