/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author aguero
 */
public class ProductStockDTO implements Serializable{
    @Serial
    private static final long serialVersionUID = 2715252716241427153L;
    private Integer stock;
    private BigDecimal price;
    public ProductStockDTO() {
    }
    public ProductStockDTO(Integer stock, BigDecimal price) {
        this.stock = stock;
        this.price = price;
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return "ProductStockInfo [stock=" + stock + ", price=" + price + "]";
    }
    

}
