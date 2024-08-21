/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller.report;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.angel.dto.ProductDTO;
import com.github.angel.dto.ReportDTO;
import com.github.angel.service.ProductService;
import com.github.angel.service.ReportService;
import com.github.angel.utils.PageRenderUtils;
import com.itextpdf.text.DocumentException;

/**
 *
 * @author aguero
 */
@PreAuthorize("hasAuthority('READ')")
@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final ProductService productService;

    public ReportController(ReportService reportService, ProductService productService) {
        this.reportService = reportService;
        this.productService = productService;
    }

    // Obtén la lista de productos y métodos de pago desde el servicio
    @ModelAttribute("paymentMethods")
    public List<String> paymentMethods() {
        return List.of("Credit Card", "PayPal", "Bank Transfer");
    }

    @ModelAttribute("products")
    public List<ProductDTO> getProducts() {
        return productService.getProductsAllName();
    }

    @ModelAttribute("MostSellingCategory")
    public Long getSellingCategory() {
        return reportService.getSellingCategory();
    }

    @ModelAttribute("MostSellingProduct")
    public Long getSellingProduct() {
        return reportService.getSellingProduct();
    }

    @ModelAttribute("TotalTransactions")
    public long getTotalTransactions() {
        return reportService.getTotalTransactions();
    }

    @ModelAttribute("TotalSales")
    public long getTotalSales() {
        return reportService.getTotalSales();
    }

    @GetMapping("/sales")
    public String getsaleReport(final Model model, @RequestParam(name = "pages", defaultValue = "0") int pages) {
        Pageable pageable = PageRequest.of(pages, 5);
        // Aplica los filtros seleccionados en la lógica de búsqueda
        // Determina si se deben aplicar filtros

        Page<ReportDTO> report = reportService.findAll(pageable);

        PageRenderUtils<ReportDTO> pageRenderUtils = new PageRenderUtils<>("/report/sales", report);
        model.addAttribute("pageRenderUtils", pageRenderUtils);
        model.addAttribute("transactions", report);

        return "reports/sales-report";
    }

    @GetMapping("/transaction/generate-pdf/{reportId}")
    public ResponseEntity<byte[]> generatePdfReport(@PathVariable("reportId") Long reportId) {
        try {
            byte[] pdfBytes = reportService.generatePurchaseReport(reportId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("filename", "purchase-report.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (DocumentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
