package com.github.angel.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;


public class PurchaseDTO implements Serializable {
        @Serial
        private static final long serialVersionUID = -3716351729173619183L;
        private Long purchaseId;

        private Long customerId;
        private Long productId;

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

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
        private LocalDateTime updateAt;

        public PurchaseDTO() {
        }




      



        public PurchaseDTO(Long purchaseId, Long customerId, Long productId,
                        @PositiveOrZero(message = "Quantity must be a positive value or zero") @NotNull(message = "Quantity is required") Integer quantity,
                        @Positive(message = "Price per unit must be a positive value") BigDecimal pricePerUnit,
                        @DecimalMin(value = "0.0", inclusive = false, message = "Total price must be greater than zero") @Digits(integer = 10, fraction = 2, message = "Total price should be a valid decimal number with up to 10 digits and 2 decimal places") BigDecimal totalPrice,
                        @NotBlank(message = "Shipping address is required") @Size(max = 150, message = "Shipping address must not exceed 150 characters") String shippingAddress,
                        @NotBlank(message = "Payment method is required") @Size(max = 50, message = "Payment method must not exceed 50 characters") String paymentMethod,
                        LocalDateTime purchaseDate,
                        @NotBlank(message = "Note is required") @Size(max = 200, message = "Note must be at most 200 characters long") String note,
                        LocalDateTime updateAt) {
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
        }








        public Long getPurchaseId() {
                return purchaseId;
        }

        public void setPurchaseId(Long purchaseId) {
                this.purchaseId = purchaseId;
        }

        public Long getCustomerId() {
                return customerId;
        }

        public void setCustomerId(Long customerId) {
                this.customerId = customerId;
        }

        public Long getProductId() {
                return productId;
        }

        public void setProductId(Long productId) {
                this.productId = productId;
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

        public LocalDateTime getUpdateAt() {
                return updateAt;
        }

        public void setUpdateAt(LocalDateTime updateAt) {
                this.updateAt = updateAt;
        }

        

        @Override
        public String toString() {
                return "PurchaseDTO [purchaseId=" + purchaseId + ", customerId=" + customerId + ", productId="
                                + productId + ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit
                                + ", totalPrice=" + totalPrice + ", shippingAddress=" + shippingAddress
                                + ", paymentMethod=" + paymentMethod + ", purchaseDate=" + purchaseDate + ", note="
                                + note + ", updateAt=" + updateAt + "]";
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
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
                PurchaseDTO other = (PurchaseDTO) obj;
                if (purchaseId == null) {
                        if (other.purchaseId != null)
                                return false;
                } else if (!purchaseId.equals(other.purchaseId))
                        return false;
                return true;
        }

}
