/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.dto;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author aguero
 */
public class PurchaseValidationDTO implements Serializable{
    @Serial
    private static final long serialVersionUID = -2735391261623032717L;
    private boolean customerExists;
    private boolean productExists;
    public PurchaseValidationDTO() {
    }
    public PurchaseValidationDTO(boolean customerExists, boolean productExists) {
        this.customerExists = customerExists;
        this.productExists = productExists;
    }
    public boolean isCustomerExists() {
        return customerExists;
    }
    public void setCustomerExists(boolean customerExists) {
        this.customerExists = customerExists;
    }
    public boolean isProductExists() {
        return productExists;
    }
    public void setProductExists(boolean productExists) {
        this.productExists = productExists;
    }
    @Override
    public String toString() {
        return "PurchaseValidationResult [customerExists=" + customerExists + ", productExists=" + productExists + "]";
    }

}
