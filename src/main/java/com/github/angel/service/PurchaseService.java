/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.angel.dto.PurchaseDTO;

/**
 *
 * @author aguero
 */
public interface PurchaseService {
    Page<PurchaseDTO> getAllPurchases(Pageable pageable);
    PurchaseDTO getPurchaseById(Long id);
    void createPurchase(PurchaseDTO purchaseDTO);
    void updatePurchase(Long id, PurchaseDTO purchaseDTO);
    void deletePurchase(Long id);

  

    

}
