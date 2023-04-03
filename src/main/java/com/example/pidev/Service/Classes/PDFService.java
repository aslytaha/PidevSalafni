package com.example.pidev.Service.Classes;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.pidev.Entities.ClientAccount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
@AllArgsConstructor
public class PDFService {

    public void export(HttpServletResponse response, List<ClientAccount> clients) throws Exception, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Liste des clients", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        for (ClientAccount client : clients) {
            Paragraph clientParagraph = new Paragraph("Nom: " + client.getSubscription() + ", Prénom: " + client.getSolde(), fontParagraph);
            document.add(clientParagraph);
        }

        document.close();
    }
}

