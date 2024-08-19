/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.angel.dto.ProductDTO;
import com.github.angel.entity.Product;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.ProductRepository;
import com.github.angel.service.ProductService;

/**
 *
 * @author aguero
 */

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        return productRepository.findAllDtosPage(pageable);
         
    }
    @Transactional
    @Override
    public void createProduct(ProductDTO dto) {
        Product product = mapProductDTOToProduct(dto);
        productRepository.persist(product);
    }

    @Transactional
    @Override
    public void updateProduct(ProductDTO dto, Long productId) {
        if (productRepository.existsById(productId)) {
            Product product = mapProductDTOToProduct(dto);
            product.setProductId(productId);
            productRepository.update(product);
        } else {
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }
    }

    @Transactional
    @Override
    public void deleteProduct(Long productId) {
        if (productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
        } else {
            throw new IllegalArgumentException("Product with id " + productId + " does not exist");
        }

    }
    @Transactional(readOnly = true)
    @Override
    public ProductDTO getProductById(Long productId) {
        return productRepository.findByProductIdDto(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAllProductDTOs();
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> searchProducts(String name) {
        return productRepository.findByName(name);
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> searchProductsByCategory(Long categoryId, String name) {
       
        return productRepository.findByProductsByCategory(categoryId, name);
    }


    private Product mapProductDTOToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setCategoryId(dto.getCategoryId());
        product.setName(dto.getName());
        product.setStock(dto.getStock());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }
    @Transactional(readOnly = true)
    @Override
    public List<ProductDTO> getProductsAllName() {
        return productRepository.findAllName();
    }

    
}
