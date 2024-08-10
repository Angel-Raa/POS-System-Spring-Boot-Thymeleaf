package com.github.angel.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductDTO(
        Long productId,

        @NotBlank(message = "Name is required")
        @Size(max = 40, message = "Name must not exceed 40 characters")
        String name,

        @Positive(message = "Price must be greater than zero")
        @NotNull(message = "Price is required")
        BigDecimal price,

        @PositiveOrZero(message = "Stock must be zero or positive")
        @NotNull(message = "Stock is required")
        Integer stock,

        @NotBlank(message = "Description is required")
        @Size(max = 150, message = "Description must not exceed 150 characters")
        String description,

        @NotNull(message = "Category is required")
        Long categoryId
) {
}
