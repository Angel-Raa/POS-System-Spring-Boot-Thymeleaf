/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.angel.dto.ProductStockDTO;
import com.github.angel.dto.PurchaseDTO;
import com.github.angel.dto.PurchaseValidationDTO;
import com.github.angel.entity.Purchase;
import com.github.angel.exception.InsufficientStockException;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.ProductRepository;
import com.github.angel.repository.PurchaseRepository;
import com.github.angel.service.PurchaseService;

/**
 *
 * @author aguero
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository,
            ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PurchaseDTO> getAllPurchases(Pageable pageable) {
        return purchaseRepository.findAllPage(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public PurchaseDTO getPurchaseById(Long id) {
        return purchaseRepository.findByPurchaseIdDto(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
    }

    @Transactional
    @Override
    public void createPurchase(PurchaseDTO purchaseDTO) {
        Long productId = purchaseDTO.getProductId();
        Long customerId = purchaseDTO.getCustomerId();
        PurchaseValidationDTO validationResult = purchaseRepository.purchaseValidation(customerId, productId);
        // Verificar si el cliente existe
        if (!validationResult.isCustomerExists()) {
            throw new ResourceNotFoundException("Customer not found");
        }
        // Verificar si el producto existe
        if (!validationResult.isProductExists()) {
            throw new ResourceNotFoundException("Product not found");
        }
        // Verificar que haya suficiente stock
        ProductStockDTO productStockDTO = productRepository.findProductStocByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        validateStock(productStockDTO, purchaseDTO.getQuantity());
        if (productStockDTO.getStock() < purchaseDTO.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock for product with id: " + productId);
        }
        // Reducir el stock del producto
        int newStock = productStockDTO.getStock() - purchaseDTO.getQuantity();
        productRepository.updateStock(productId, newStock);
        // Calcular el precio total
        BigDecimal totalPrice = productStockDTO.getPrice().multiply(BigDecimal.valueOf(purchaseDTO.getQuantity()));
        BigDecimal pricePerUnit = productStockDTO.getPrice();
        // Configurar otros campos de la compra
        Purchase purchase = mapToPurchaseDto(purchaseDTO);
        purchase.setTotalPrice(totalPrice);
        purchase.setPricePerUnit(pricePerUnit);
        // Guardar la compra en la base de datos
        purchaseRepository.persist(purchase);
    }

    private Purchase mapToPurchaseDto(PurchaseDTO dto) {
        Purchase purchase = new Purchase();
        purchase.setPurchaseId(dto.getPurchaseId());
        purchase.setCustomerId(dto.getCustomerId());
        purchase.setProductId(dto.getProductId());
        purchase.setQuantity(dto.getQuantity());
        purchase.setPricePerUnit(dto.getPricePerUnit());
        purchase.setTotalPrice(dto.getTotalPrice());
        purchase.setShippingAddress(dto.getShippingAddress());
        purchase.setPaymentMethod(dto.getPaymentMethod());
        purchase.setPurchaseDate(dto.getPurchaseDate());
        purchase.setNote(dto.getNote());
        purchase.setUpdateAt(dto.getUpdateAt());
        return purchase;
    }

    private void validateStock(ProductStockDTO product, Integer requestedQuantity) {
        Objects.requireNonNull(product, "Product cannot be null");
        Objects.requireNonNull(requestedQuantity, "Requested quantity cannot be null");

        if (requestedQuantity <= 0) {
            throw new IllegalArgumentException("Requested quantity must be a positive number");
        }

        Integer availableStock = product.getStock();
        if (availableStock == null) {
            throw new IllegalStateException("Product stock is not set for product: ");
        }

        if (availableStock < requestedQuantity) {
            throw new InsufficientStockException(String.format(
                    "Insufficient stock for. Available: %d, Requested: %d",
                    availableStock, requestedQuantity));
        }
    }

}
