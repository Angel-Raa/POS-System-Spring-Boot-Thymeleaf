/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.github.angel.utils;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Component;

import com.github.angel.dto.CustomerPurchaseHistoryDTO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 *
 * @author aguero
 */
@Component
public class PdfGeneratorCustomerUtils {
    private static final BaseColor HEADER_COLOR = new BaseColor(200, 220, 255);
    private static final BaseColor ALT_ROW_COLOR = new BaseColor(245, 250, 255);
    private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 22, BaseColor.BLACK);
    private static final Font SUBTITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA, 14, BaseColor.DARK_GRAY);
    private static final Font CONTENT_FONT = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
    private static final Font FOOTER_FONT = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY);
    private static final Font HEADER_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);

    public byte[] createPdfReport(List<CustomerPurchaseHistoryDTO> purchaseHistory) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, out);
        document.open();

        addHeader(document);
        addCustomerInfo(document, purchaseHistory);
        addPurchaseHistory(document, purchaseHistory);
        addSummary(document, purchaseHistory);
        addFooter(document);

        document.close();
        return out.toByteArray();
    }

    private void addHeader(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Customer Purchase History", TITLE_FONT);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        Paragraph subtitle = new Paragraph("Detailed Purchase Information", SUBTITLE_FONT);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);

        document.add(Chunk.NEWLINE);
    }

    private void addCustomerInfo(Document document, List<CustomerPurchaseHistoryDTO> purchaseHistory)
            throws DocumentException {
        if (purchaseHistory.isEmpty())
            return;

        CustomerPurchaseHistoryDTO customerInfo = purchaseHistory.get(0);

        document.add(new Paragraph("Customer Information", SUBTITLE_FONT));
        document.add(Chunk.NEWLINE);

        PdfPTable infoTable = new PdfPTable(2); // Dos columnas para etiqueta y valor
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(5f);
        infoTable.setSpacingAfter(10f);
        infoTable.setWidths(new float[] { 1, 2 }); // Ajustar proporciones de columna

        // Estilo para las celdas de encabezado
        PdfPCell headerCell = new PdfPCell(new Phrase("Customer Information", TITLE_FONT));
        headerCell.setColspan(2);
        headerCell.setBackgroundColor(HEADER_COLOR);
        headerCell.setPadding(10);
        headerCell.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
        headerCell.setBorderColor(BaseColor.GRAY);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        infoTable.addCell(headerCell);

        // Añadir bordes a las celdas
        infoTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        infoTable.getDefaultCell().setPadding(8);
        infoTable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);

        // Añadir filas con estilo
        addCustomerInfoItem(infoTable, "Customer ID", customerInfo.getCustomerId().toString());
        addCustomerInfoItem(infoTable, "Name", customerInfo.getFirstName() + " " + customerInfo.getLastName());
        addCustomerInfoItem(infoTable, "Email", customerInfo.getEmail());
        addCustomerInfoItem(infoTable, "Phone", customerInfo.getTel());
        addCustomerInfoItem(infoTable, "Address", customerInfo.getAddress());

        document.add(infoTable);
    }

    private void addCustomerInfoItem(PdfPTable table, String label, String value) {
        // Estilo para las celdas de etiqueta
        PdfPCell labelCell = new PdfPCell(new Phrase(label + ":", HEADER_FONT));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(8);
        labelCell.setBackgroundColor(ALT_ROW_COLOR);
        labelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        labelCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        // Estilo para las celdas de valor
        PdfPCell valueCell = new PdfPCell(new Phrase(value, CONTENT_FONT));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(8);
        valueCell.setBackgroundColor(BaseColor.WHITE);
        valueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        valueCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell(labelCell);
        table.addCell(valueCell);
    }

    private void addPurchaseHistory(Document document, List<CustomerPurchaseHistoryDTO> purchaseHistory)
            throws DocumentException {
        if (purchaseHistory.isEmpty()) {
            Paragraph noData = new Paragraph("No purchase history found for this customer.", CONTENT_FONT);
            noData.setAlignment(Element.ALIGN_CENTER);
            document.add(noData);
            return;
        }

        document.add(new Paragraph("Purchase History", SUBTITLE_FONT));
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(new float[] { 2, 2, 1, 1, 1, 2, 2 });
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);

        // Add table headers
        String[] headers = { "Product", "Date", "Quantity", "Price", "Total", "Payment Method", "Shipping Address" };
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, HEADER_FONT));
            cell.setBackgroundColor(HEADER_COLOR);
            cell.setPadding(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        boolean alternateColor = false;

        for (CustomerPurchaseHistoryDTO item : purchaseHistory) {
            BaseColor bgColor = alternateColor ? ALT_ROW_COLOR : BaseColor.WHITE;
            alternateColor = !alternateColor;

            addCell(table, item.getProductName(), bgColor, Element.ALIGN_LEFT);
            addCell(table, item.getPurchaseDate().format(formatter), bgColor, Element.ALIGN_CENTER);
            addCell(table, String.valueOf(item.getQuantity()), bgColor, Element.ALIGN_RIGHT);
            addCell(table, String.format("$%.2f", item.getPricePerUnit()), bgColor, Element.ALIGN_RIGHT);
            addCell(table, String.format("$%.2f", item.getTotalPrice()), bgColor, Element.ALIGN_RIGHT);
            addCell(table, item.getPaymentMethod(), bgColor, Element.ALIGN_LEFT);
            addCell(table, item.getShippingAddress(), bgColor, Element.ALIGN_LEFT);
        }

        document.add(table);

        // Add note if present
        if (purchaseHistory.get(0).getNote() != null && !purchaseHistory.get(0).getNote().isEmpty()) {
            document.add(Chunk.NEWLINE);
            Paragraph note = new Paragraph("Note: " + purchaseHistory.get(0).getNote(), CONTENT_FONT);
            document.add(note);
        }
    }

    private void addCell(PdfPTable table, String content, BaseColor bgColor, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(content, CONTENT_FONT));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(8);
        cell.setHorizontalAlignment(alignment);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private void addCell(PdfPTable table, String content, BaseColor bgColor) {
        PdfPCell cell = new PdfPCell(new Phrase(content, CONTENT_FONT));
        cell.setBackgroundColor(bgColor);
        cell.setPadding(5);
        table.addCell(cell);
    }

    private void addSummary(Document document, List<CustomerPurchaseHistoryDTO> purchaseHistory)
            throws DocumentException {
        document.add(Chunk.NEWLINE);

        BigDecimal totalSpent = purchaseHistory.stream()
                .map(CustomerPurchaseHistoryDTO::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Paragraph summary = new Paragraph();
        summary.add(new Phrase("Total Purchases: " + purchaseHistory.size() + "\n", SUBTITLE_FONT));
        summary.add(new Phrase("Total Spent: $" + totalSpent, SUBTITLE_FONT));
        summary.setAlignment(Element.ALIGN_RIGHT);
        document.add(summary);
    }

    private void addFooter(Document document) throws DocumentException {
        document.add(Chunk.NEWLINE);
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(BaseColor.LIGHT_GRAY);
        document.add(ls);

        Paragraph footer = new Paragraph();
        footer.add(new Phrase("Generated by POS System\n", FOOTER_FONT));
        footer.add(new Phrase("Signature: Angel Aguero\n", FOOTER_FONT));
        footer.add(new Phrase("Project Code: ", FOOTER_FONT));
        footer.add(new Phrase("https://github.com/Angel-Raa/POS-Spring-Boot", FOOTER_FONT));
        footer.setAlignment(Element.ALIGN_RIGHT);

        document.add(footer);
    }
}
