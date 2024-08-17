/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.service;

import java.util.List;

import com.github.angel.dto.ProductDTO;

/**
 *
 * @author aguero
 */
public interface ProductService {
    void createProduct(ProductDTO dto);
    void updateProduct(ProductDTO dto, Long productId);
    void deleteProduct(Long productId);
    ProductDTO getProductById(Long productId);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> searchProducts(String name);
    List<ProductDTO> getProductsByCategory(Long categoryId);
    List<ProductDTO> searchProductsByCategory(Long categoryId, String name);

    


}
