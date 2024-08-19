/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.service.impl;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.github.angel.dto.ReportDTO;
import com.github.angel.exception.ReportGenerationException;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.PurchaseRepository;
import com.github.angel.service.ReportService;
import com.github.angel.utils.PdfGeneratorUtils;
import com.itextpdf.text.DocumentException;


/**
 *
 * @author aguero
 */
@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private  final PdfGeneratorUtils  generatorUtils;
    private final PurchaseRepository purchaseRepository;

    

    public ReportServiceImpl(PdfGeneratorUtils generatorUtils, PurchaseRepository purchaseRepository) {
        this.generatorUtils = generatorUtils;
        this.purchaseRepository = purchaseRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ReportDTO> findAll(Pageable pageable) {
        return purchaseRepository.findPurchaseReportBy(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public ReportDTO getReportById(Long reportId) {
        return purchaseRepository.findPurchaseReportById(reportId)
                .orElseThrow(() -> new ResourceNotFoundException("Report not found with id " + reportId));
    }

    @Transactional(readOnly = true)
    @Override
    public byte[] generatePurchaseReport(Long reportId) {
      try {
        ReportDTO data = getReportById(reportId);
        return generatorUtils.createPdfReport(data);
    } catch (DocumentException e) {
        logger.error("Error al generar el PDF: {}", e.getMessage());
        throw new ReportGenerationException("Error al generar el reporte PDF", e);
    } catch (Exception e) {
        logger.error("Error inesperado: {}", e.getMessage());
        throw new ReportGenerationException("Error inesperado al generar el reporte", e);
    }

    }

   

  
}
