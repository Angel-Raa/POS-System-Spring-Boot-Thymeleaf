/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.controller.report;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import com.github.angel.dto.ReportDTO;
import com.github.angel.service.ReportService;
import com.github.angel.utils.PageRenderUtils;
import com.itextpdf.text.DocumentException;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author aguero
 */
@Controller
@RequestMapping("/report")
public class ReportController {
    private final ReportService reportService;
    private final TemplateEngine engine;
    private final ServletContext servletContext;
   

    public ReportController(ReportService reportService, TemplateEngine engine, ServletContext servletContext) {
        this.reportService = reportService;
        this.engine = engine;
        this.servletContext = servletContext;
    }

    @GetMapping("/sales")
    public String getsaleReport(final Model model, @RequestParam(name = "pages", defaultValue = "0") int pages) {
        Pageable pageable = PageRequest.of(pages, 5);
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

      }catch(DocumentException e){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }

}
