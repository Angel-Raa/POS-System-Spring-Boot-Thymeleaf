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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

import com.github.angel.dto.ReportDTO;
import com.github.angel.exception.ResourceNotFoundException;
import com.github.angel.repository.PurchaseRepository;
import com.github.angel.service.ReportService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author aguero
 */
@Service
public class ReportServiceImpl implements ReportService {
    private static final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    private final PurchaseRepository purchaseRepository;

    public ReportServiceImpl(PurchaseRepository purchaseRepository) {
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
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // Generate PDF report using iText library
            PdfWriter.getInstance(document, out);
            document.open();
            // Add report content to PDF
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.BLACK);
            Paragraph title = new Paragraph("Purchase Report", titleFont);
            title.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            addTableRow(table, "Purchase number", data.getReportId().toString());
            addTableRow(table, "Client name", data.getCustomerName());
            addTableRow(table, "Product name", data.getProductName());

            addTableRow(table, "Quantity", data.getQuantity().toString());
            addTableRow(table, "Price Per Unit", "$ " + data.getPricePerUnit().toString());
            addTableRow(table, "Purchase Date", data.getPurchaseDate().format(formatter));
            addTableRow(table, "Payment Method", data.getPaymentMethod());

            document.add(table);
            document.close();
            return out.toByteArray();

        } catch (DocumentException e) {
            logger.error("Error al generar el PDF: {}", e.getMessage());
           
        } catch (Exception e) {
            logger.error("Error inesperado: {}", e.getMessage());
        }
        return null;

    }

    private void addTableRow(PdfPTable table, String key, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(key));
        PdfPCell cell2 = new PdfPCell(new Phrase(value));
        table.addCell(cell1);
        table.addCell(cell2);
    }

  
}
