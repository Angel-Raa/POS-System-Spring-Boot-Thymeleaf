/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.github.angel.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.angel.dto.ReportDTO;
import com.itextpdf.text.DocumentException;

/**
 *
 * @author aguero
 */
public interface ReportService {

    Page<ReportDTO> findAll(Pageable pageable);
    Page<ReportDTO> getAllWithFilters(String productName, String paymentMethod, Pageable pageable );

    ReportDTO getReportById(Long reportId);
    byte[] generatePurchaseReport(Long reportId) throws DocumentException;
    long getTotalSales();
    long getTotalTransactions();
    long getSellingProduct();
    long getSellingCategory();
   


}
