/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.repository;

import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.github.angel.dto.PurchaseDTO;
import com.github.angel.dto.PurchaseValidationDTO;
import com.github.angel.dto.ReportDTO;
import com.github.angel.entity.Purchase;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

/**
 *
 * @author aguero
 */
@Repository
public interface PurchaseRepository
      extends ListPagingAndSortingRepository<Purchase, Long>, BaseJpaRepository<Purchase, Long> {
   @Query(value = """
         SELECT NEW com.github.angel.dto.PurchaseDTO(
            p.purchaseId,p.customerId, p.productId, p.quantity, p.pricePerUnit, p.totalPrice, p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note, p.updateAt
         ) FROM Purchase p
         """, countQuery = "SELECT COUNT(p.purchaseId) FROM Purchase p")
   Page<PurchaseDTO> findAllPage(Pageable pageable);

   @Query(value = """
         SELECT NEW com.github.angel.dto.PurchaseDTO(
            p.purchaseId,p.customerId, p.productId, p.quantity, p.pricePerUnit, p.totalPrice, p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note, p.updateAt
         ) FROM Purchase p
         WHERE p.purchaseId = :purchaseId
         """)
   Optional<PurchaseDTO> findByPurchaseIdDto(@Param("purchaseId") Long purchaseId);

   @Query(value = """
         SELECT NEW  com.github.angel.dto.PurchaseDTO(
            p.purchaseId,p.customerId, p.productId, p.quantity, p.pricePerUnit, p.totalPrice, p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note, p.updateAt
         ) FROM Purchase p
         ORDER BY p.purchaseDate
         """, countQuery = "SELECT COUNT(p.purchaseId) FROM Purchase p")
   Page<PurchaseDTO> findByOrderByPurchaseDate(Pageable pageable);

   @Query(value = """
             SELECT NEW com.github.angel.dto.PurchaseDTO(
                    p.purchaseId,p.customerId, p.productId, p.quantity, p.pricePerUnit, p.totalPrice, p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note, p.updateAt
                 ) FROM Purchase p
                  WHERE p.customerId = :customerId
                  ORDER BY p.purchaseDate
         """)
   List<PurchaseDTO> findByCustomerIdOrderByPurchaseDate(@Param("customerId") Long customerId);

   @Query("""
         SELECT NEW com.github.angel.dto.PurchaseValidationDTO(
                    COUNT(DISTINCT c) > 0,
                    COUNT(DISTINCT p) > 0
                )
                FROM Customer c, Product p
                WHERE c.customerId = :customerId AND p.productId = :productId
            """)
   PurchaseValidationDTO purchaseValidation(@Param("customerId") Long customerId,
         @Param("productId") Long productId);

   @Query("""
         SELECT NEW com.github.angel.dto.PurchaseDTO(p.purchaseId,  p.quantity) FROM Purchase p
         WHERE p.purchaseId = :purchaseId
         """)
   Optional<PurchaseDTO> findPurchaseDetailsById(@Param("purchaseId") Long purchaseId);

   @Query("""
         SELECT NEW com.github.angel.dto.ReportDTO(
            p.purchaseId, c.firstName, pr.name, p.quantity, p.pricePerUnit, p.totalPrice,
            p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note
         )
         FROM Purchase p
         JOIN Product pr ON p.productId = pr.productId
         JOIN Customer c ON p.customerId = c.customerId
            WHERE p.purchaseId = :purchaseId
         """)
   Optional<ReportDTO> findPurchaseReportById(@Param("purchaseId") Long purchaseId);

   @Query("""
         SELECT NEW com.github.angel.dto.ReportDTO(
            p.purchaseId, c.firstName, pr.name, p.quantity, p.pricePerUnit, p.totalPrice,
            p.shippingAddress, p.paymentMethod, p.purchaseDate, p.note
         )
         FROM Purchase p
         JOIN Product pr ON p.productId = pr.productId
         JOIN Customer c ON p.customerId = c.customerId
         """)
   Page<ReportDTO> findPurchaseReportBy(Pageable pageable);

   @Query("""
         SELECT COALESCE(SUM(p.totalPrice), 0)
         FROM Purchase p
         """)
   Long findTotalSales();

   @Query("""
         SELECT COALESCE(COUNT(p.purchaseId), 0)
         FROM Purchase p
         """)
   Long findTotalTransactions();

   @Query("""
         SELECT COALESCE(SUM(p.quantity), 0)
         FROM Purchase p
         """)
   Long findSellingProduct();

   @Query("""
         SELECT COALESCE(COUNT(DISTINCT p.productId), 0)
         FROM Purchase p
         """)
   Long findSellingCategory();

   @Query("""
         SELECT NEW com.github.angel.dto.ReportDTO(
             p.purchaseId,
             c.firstName,
             pr.name,
             p.quantity,
             p.pricePerUnit,
             p.totalPrice,
             p.shippingAddress,
             p.paymentMethod,
             p.purchaseDate,
             p.note
         )
         FROM Purchase p
         JOIN p.product pr
         JOIN p.customer c
         WHERE (:productName IS NULL OR pr.name = :productName)
         AND (:paymentMethod IS NULL OR p.paymentMethod = :paymentMethod)
         """)
   Page<ReportDTO> findAllWithFilters(
         @Param("productName") String productName,
         @Param("paymentMethod") String paymentMethod,
         Pageable pageable);

}
