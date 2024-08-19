/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
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
public class ReportDTO implements Serializable {
    @Serial
    private static final Long serialVersionUID = -8271626154612192716L;
    private Long reportId;

    private String customerName;
    private String productName;

    @PositiveOrZero(message = "Quantity must be a positive value or zero")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @Positive(message = "Price per unit must be a positive value")
    private BigDecimal pricePerUnit;

    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places")
    private BigDecimal totalPrice;

    @NotBlank(message = "Shipping address is required")
    @Size(max = 150, message = "Shipping address must not exceed 150 characters")
    private String shippingAddress;

    @NotBlank(message = "Payment method is required")
    @Size(max = 50, message = "Payment method must not exceed 50 characters")
    private String paymentMethod;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDateTime purchaseDate;

    @NotBlank(message = "Note is required")
    @Size(max = 200, message = "Note must be at most 200 characters long")
    private String note;

    public ReportDTO() {
    }

    public ReportDTO(Long reportId, String customerName, String productName,
            @PositiveOrZero(message = "Quantity must be a positive value or zero") @NotNull(message = "Quantity is required") Integer quantity,
            @Positive(message = "Price per unit must be a positive value") BigDecimal pricePerUnit,
            @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places") BigDecimal totalPrice,
            @NotBlank(message = "Shipping address is required") @Size(max = 150, message = "Shipping address must not exceed 150 characters") String shippingAddress,
            @NotBlank(message = "Payment method is required") @Size(max = 50, message = "Payment method must not exceed 50 characters") String paymentMethod,
            LocalDateTime purchaseDate,
            @NotBlank(message = "Note is required") @Size(max = 200, message = "Note must be at most 200 characters long") String note) {
        this.reportId = reportId;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.purchaseDate = purchaseDate;
        this.note = note;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
