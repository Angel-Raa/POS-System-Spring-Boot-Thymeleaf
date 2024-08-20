/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.angel.dto.CustomerPurchaseHistoryDTO;
import com.github.angel.service.ReportService;
import com.github.angel.utils.PageRenderUtils;
import com.itextpdf.text.DocumentException;

import jakarta.validation.constraints.Min;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/report-customer")
public class CustomerReportController {
    private final ReportService reportService;

    public CustomerReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/details/{customerId}")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public String getCustomerReport(final Model model, @PathVariable("customerId") @Min(1) Long customerId,
            @RequestParam(name = "pages", defaultValue = "0") int pages) {
        Pageable pageable = PageRequest.of(pages, 5);
        Page<CustomerPurchaseHistoryDTO> curtomers = reportService.getCustomerPurchaseHistory(customerId, pageable);
        PageRenderUtils<CustomerPurchaseHistoryDTO> pageRenderUtils = new PageRenderUtils<>(
                "/report-customer/" + customerId, curtomers);
        model.addAttribute("pageRenderUtils", pageRenderUtils);
        model.addAttribute("customerId", customerId);
        model.addAttribute("transactions", curtomers);
        return "reports/customer";
    }

    @GetMapping("/generate-pdf/{customerId}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable("customerId") Long customerId) throws DocumentException {
        byte[] pdfBytes = reportService.generatePurchaseHistoryPdf(customerId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customer-report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }

}
