package com.github.angel.dto;

import jakarta.validation.constraints.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

public class ProductDTO implements Serializable {
        @Serial
        private static final long serialVersionUID = -1261525123133092817L;
        private Long productId;
        @NotBlank(message = "Name is required")
        @Size(max = 40, message = "Name must not exceed 40 characters")
        private String name;
        @Positive(message = "Price must be greater than zero")
        @NotNull(message = "Price is required")
        private BigDecimal price;
        @PositiveOrZero(message = "Stock must be zero or positive")
        @NotNull(message = "Stock is required")
        private Integer stock;
        @NotBlank(message = "Description is required")
        @Size(max = 150, message = "Description must not exceed 150 characters")
        private String description;
        @NotNull(message = "Category is required")
        private Long categoryId;

        public ProductDTO() {
        }

        public ProductDTO(Long productId,
                        @NotBlank(message = "Name is required") @Size(max = 40, message = "Name must not exceed 40 characters") String name,
                        @Positive(message = "Price must be greater than zero") @NotNull(message = "Price is required") BigDecimal price,
                        @PositiveOrZero(message = "Stock must be zero or positive") @NotNull(message = "Stock is required") Integer stock,
                        @NotBlank(message = "Description is required") @Size(max = 150, message = "Description must not exceed 150 characters") String description,
                        @NotNull(message = "Category is required") Long categoryId) {
                this.productId = productId;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.description = description;
                this.categoryId = categoryId;
        }

        public Long getProductId() {
                return productId;
        }

        public String getName() {
                return name;
        }

        public BigDecimal getPrice() {
                return price;
        }

        public Integer getStock() {
                return stock;
        }

        public String getDescription() {
                return description;
        }

        public Long getCategoryId() {
                return categoryId;
        }

        public void setProductId(Long productId) {
                this.productId = productId;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setPrice(BigDecimal price) {
                this.price = price;
        }

        public void setStock(Integer stock) {
                this.stock = stock;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public void setCategoryId(Long categoryId) {
                this.categoryId = categoryId;
        }

        @Override
        public String toString() {
                return "ProductDTO [productId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock
                                + ", description=" + description + ", categoryId=" + categoryId + "]";
        }

}
