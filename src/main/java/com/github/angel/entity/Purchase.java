/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 *
 * @author aguero
 */

@Entity(name = "Purchase")
@Table(name = "purchases")
public class Purchase implements Serializable {
    @Serial
    private static final long serialVersionUID = 182615210281710381L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_id")
    private Long purchaseId;
    @Column(name = "customer_id", nullable = false, insertable = true, updatable = true)
    private Long customerId;
    @Column(name = "product_id", nullable = false, insertable = true, updatable = true)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    @PositiveOrZero(message = "Quantity must be a positive value or zero")
    @NotNull(message = "Quantity is required")
    private Integer quantity;

    @Column(name = "price_per_unit", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Price per unit must be greater than zero")
    @Digits(integer = 6, fraction = 2, message = "Price per unit should be a valid decimal number with up to 6 digits and 2 decimal places")
    @NotNull(message = "Price per unit is required")
    private BigDecimal pricePerUnit;

    @Column(name = "total_price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero")
    @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places")
    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;

    @Column(name = "shipping_address")
    @Size(max = 100, message = "Shipping address must be at most 100 characters long")
    @NotBlank(message = "Shipping address cannot be empty")
    private String shippingAddress;

    @Column(name = "payment_method")
    @Size(max = 50, message = "Payment method must be at most 50 characters long")
    @NotBlank(message = "Payment method cannot be empty")
    private String paymentMethod;

    @Column(name = "purchase_date")
    @CreationTimestamp
    private LocalDateTime purchaseDate;

    @NotBlank(message = "Note is required")
    @Size(max = 250, message = "Note must be at most 250 characters long")
    @Column(length = 250, name = "note")
    @JdbcTypeCode(java.sql.Types.LONGVARCHAR)
    @Lob
    private String note;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", insertable = false, updatable = false)
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, targetEntity = Product.class)
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    public Purchase() {
    }

    
    public Purchase(Long purchaseId, Long customerId, Long productId,
            @PositiveOrZero(message = "Quantity must be a positive value or zero") @NotNull(message = "Quantity is required") Integer quantity,
            @DecimalMin(value = "0.0", inclusive = false, message = "Price per unit must be greater than zero") @Digits(integer = 6, fraction = 2, message = "Price per unit should be a valid decimal number with up to 6 digits and 2 decimal places") @NotNull(message = "Price per unit is required") BigDecimal pricePerUnit,
            @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places") @NotNull(message = "Total price is required") BigDecimal totalPrice,
            @Size(max = 100, message = "Shipping address must be at most 100 characters long") @NotBlank(message = "Shipping address cannot be empty") String shippingAddress,
            @Size(max = 50, message = "Payment method must be at most 50 characters long") @NotBlank(message = "Payment method cannot be empty") String paymentMethod,
            LocalDateTime purchaseDate,
            @NotBlank(message = "Note is required") @Size(max = 250, message = "Note must be at most 250 characters long") String note,
            LocalDateTime updateAt, Customer customer, Product product) {
        this.purchaseId = purchaseId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.purchaseDate = purchaseDate;
        this.note = note;
        this.updateAt = updateAt;
        this.customer = customer;
        this.product = product;
    }


    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public @PositiveOrZero(message = "Quantity must be a positive value or zero") @NotNull(message = "Quantity is required") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(
            @PositiveOrZero(message = "Quantity must be a positive value or zero") @NotNull(message = "Quantity is required") Integer quantity) {
        this.quantity = quantity;
    }

    public @DecimalMin(value = "0.0", inclusive = false, message = "Price per unit must be greater than zero") @Digits(integer = 6, fraction = 2, message = "Price per unit should be a valid decimal number with up to 6 digits and 2 decimal places") @NotNull(message = "Price per unit is required") BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(
            @DecimalMin(value = "0.0", inclusive = false, message = "Price per unit must be greater than zero") @Digits(integer = 6, fraction = 2, message = "Price per unit should be a valid decimal number with up to 6 digits and 2 decimal places") @NotNull(message = "Price per unit is required") BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places") @NotNull(message = "Total price is required") BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(
            @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places") @NotNull(message = "Total price is required") BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public @Size(max = 50, message = "Payment method must be at most 50 characters long") @NotBlank(message = "Payment method cannot be empty") String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(
            @Size(max = 50, message = "Payment method must be at most 50 characters long") @NotBlank(message = "Payment method cannot be empty") String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public @Size(max = 150, message = "Shipping address must be at most 150 characters long") @NotBlank(message = "Shipping address cannot be empty") String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(
            @Size(max = 150, message = "Shipping address must be at most 150 characters long") @NotBlank(message = "Shipping address cannot be empty") String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate( LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

   
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }


    @Override
    public String toString() {
        return "Purchase [purchaseId=" + purchaseId + ", customerId=" + customerId + ", productId=" + productId
                + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit + ", totalPrice=" + totalPrice
                + ", shippingAddress=" + shippingAddress + ", paymentMethod=" + paymentMethod + ", purchaseDate="
                + purchaseDate + ", note=" + note + ", updateAt=" + updateAt + ", customer=" + customer + ", product="
                + product + "]";
    }

  

}
