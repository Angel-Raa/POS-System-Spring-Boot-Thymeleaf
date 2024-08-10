package com.github.angel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PurchaseDTO(
        Long purchaseId,

        @NotNull(message = "Customer ID is required")
        Long customerId,

        @NotNull(message = "Product ID is required")
        Long productId,

        @Positive(message = "Quantity must be a positive value")
        @NotNull(message = "Quantity is required")
        Integer quantity,

        @Positive(message = "Price per unit must be a positive value")
        @NotNull(message = "Price per unit is required")
        BigDecimal pricePerUnit,

        @Positive(message = "Total price must be a positive value")
        @NotNull(message = "Total price is required")
        BigDecimal totalPrice,

        @NotBlank(message = "Shipping address is required")
        @Size(max = 150, message = "Shipping address must not exceed 150 characters")
        String shippingAddress,

        @NotBlank(message = "Payment method is required")
        @Size(max = 50, message = "Payment method must not exceed 50 characters")
        String paymentMethod,

        @NotNull(message = "Purchase date is required")
        LocalDateTime purchaseDate
) {
}
