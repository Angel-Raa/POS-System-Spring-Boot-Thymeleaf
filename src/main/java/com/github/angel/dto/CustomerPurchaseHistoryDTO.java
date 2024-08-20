/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author aguero
 */
public class CustomerPurchaseHistoryDTO implements Serializable {
    private static final long serialVersionUID = -1028172617161927111L;
    private Long customerId;
    private Long purchaseId;
    private String firstName;
    private String lastName;
    private String email;
    private String tel;
    private String address;

    private String productName;
    private Integer quantity;
    private BigDecimal pricePerUnit;
    private BigDecimal totalPrice;
    private String shippingAddress;
    private String paymentMethod;
    private LocalDateTime purchaseDate;
    private String note;

    public CustomerPurchaseHistoryDTO() {
    }

    public CustomerPurchaseHistoryDTO(Long customerId, Long purchaseId, String firstName, String lastName, String email,
            String tel, String address, String productName, Integer quantity, BigDecimal pricePerUnit,
            BigDecimal totalPrice, String shippingAddress, String paymentMethod, LocalDateTime purchaseDate,
            String note) {
        this.customerId = customerId;
        this.purchaseId = purchaseId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.tel = tel;
        this.address = address;
        this.productName = productName;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.purchaseDate = purchaseDate;
        this.note = note;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    @Override
    public String toString() {
        return "CustomerPurchaseHistoryDTO [customerId=" + customerId + ", purchaseId=" + purchaseId + ", firstName="
                + firstName + ", lastName=" + lastName + ", email=" + email + ", tel=" + tel + ", address=" + address
                + ", productName=" + productName + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit
                + ", totalPrice=" + totalPrice + ", shippingAddress=" + shippingAddress + ", paymentMethod="
                + paymentMethod + ", purchaseDate=" + purchaseDate + ", note=" + note + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((customerId == null) ? 0 : customerId.hashCode());
        result = prime * result + ((purchaseId == null) ? 0 : purchaseId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerPurchaseHistoryDTO other = (CustomerPurchaseHistoryDTO) obj;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        if (purchaseId == null) {
            if (other.purchaseId != null)
                return false;
        } else if (!purchaseId.equals(other.purchaseId))
            return false;
        return true;
    }

}
