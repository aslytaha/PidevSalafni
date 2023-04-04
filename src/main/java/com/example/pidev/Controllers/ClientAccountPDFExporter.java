package com.example.pidev.Controllers;

import com.example.pidev.Entities.ClientAccount;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ClientAccountPDFExporter {
    private List<ClientAccount> listclientAccount;

    public ClientAccountPDFExporter(List<ClientAccount> listclientAccount) {
        this.listclientAccount = listclientAccount;
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.CYAN);




        cell.setPhrase(new Phrase("rib ClientAccount"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("IDClient"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Subscription"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ClientAccount Solde"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("ClientAccount ExpirationDate "));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {
        for (ClientAccount clientAccount: listclientAccount) {
            table.addCell(String.valueOf(clientAccount.getIDClient()));
            table.addCell(String.valueOf(clientAccount.getSubscription()));
            table.addCell(String.valueOf(clientAccount.getSolde()));
            table.addCell(String.valueOf(clientAccount.getExpirationDate()));

        }
    }

    void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();

        Paragraph title = new Paragraph("List of all ClinetAccount");
        document.add(title);
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }



}
