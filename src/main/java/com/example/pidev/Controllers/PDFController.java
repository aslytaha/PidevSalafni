package com.example.pidev.Controllers;

import com.example.pidev.Entities.ClientAccount;
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
import com.example.pidev.Services.ClientAccountService;
import com.example.pidev.Services.PDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController


public class PDFController {
    @Autowired
    PDFService pdfService;
    @Autowired
    ClientAccountService clientAccountService;
    public PDFController(PDFService pdfService)
    {  this.pdfService = pdfService;
    }


    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf.." + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<ClientAccount> clients = clientAccountService.getAllClientAccount();
        pdfService.export(response, clients);
    }



}
