package com.example.pidev.Entities;
import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PDF {

    private List<ClientAccount> clientAccounts;

//    public PdfExporter(List<ClientAccount> clientAccountList) {
//        this.clientAccountList = clientAccountList;
//    }



    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("IDClient", font));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Solde", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Subscription", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("ExpirationDate", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("rib", font));
        table.addCell(cell);

    }


    private void writeTableData(PdfPTable table) {
        for (ClientAccount clientAccount : clientAccounts) {
            table.addCell(String.valueOf(clientAccount.getIDClient()));
            table.addCell(clientAccount.getExpirationDate());
            table.addCell(clientAccount.getSubscription().toString());
            table.addCell(String.valueOf(clientAccount.getRib()));
            table.addCell(String.valueOf(clientAccount.getSolde()));

        }
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException  {


        com.lowagie.text.Document document = new com.lowagie.text.Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        com.lowagie.text.Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();

    }


}
