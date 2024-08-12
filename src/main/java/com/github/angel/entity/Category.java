package com.github.angel.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categories", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Category implements Serializable {
    @Serial
    private static long serialVersionUID = 182715381639163161L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NotBlank(message = "Category name is required")
    @Size(max = 50, message = "Category name must not exceed 50 characters")
    @Column(length = 50, nullable = false, unique = true)
    private String name;
    @Size(max = 150, message = "Description must not exceed 150 characters")
    @Column(length = 150)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity =  Product.class, mappedBy = "category", orphanRemoval = true)
    private List<Product> products;

    public Category() {
    }

    public Category(Long categoryId, String description, String name, List<Product> products) {
        this.categoryId = categoryId;
        this.description = description;
        this.name = name;
        this.products = products;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public @NotBlank(message = "Category name is required") @Size(max = 50, message = "Category name must not exceed 50 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Category name is required") @Size(max = 50, message = "Category name must not exceed 50 characters") String name) {
        this.name = name;
    }

    public @Size(max = 150, message = "Description must not exceed 150 characters") String getDescription() {
        return description;
    }

    public void setDescription(@Size(max = 150, message = "Description must not exceed 150 characters") String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Category{");
        sb.append("categoryId=").append(categoryId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", products=").append(products);
        sb.append('}');
        return sb.toString();
    }
}
