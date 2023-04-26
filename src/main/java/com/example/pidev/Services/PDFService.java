package com.example.pidev.Services;

import com.example.pidev.Entities.ClientAccount;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
            Paragraph clientParagraph = new Paragraph("Subscription: " + client.getSubscription() + ", Solde: " + client.getSolde()+", id:"+ client.getIDClient(), fontParagraph);
            document.add(clientParagraph);
        }

        document.close();
    }
}

