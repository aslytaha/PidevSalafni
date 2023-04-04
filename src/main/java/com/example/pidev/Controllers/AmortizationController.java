//package com.example.pidev.Controllers;
//
//
//import com.example.pidev.Entities.Amortization;
//import com.example.pidev.Entities.LoanProject;
//import com.example.pidev.Services.AmotizationService;
//import com.example.pidev.Services.LoanProjectServiceImpl;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@RestController
//@AllArgsConstructor
//
//@RequestMapping("/Amortization")
//public class AmortizationController {
//
//    @Autowired
//    LoanProjectServiceImpl loan;
//    @Autowired
//    AmotizationService serv;
//
//
//}
//
//
//
////    @GetMapping("/loan-project/{id}/amortization-table")
////    public List<Amortization> generateAmortizationTableForLoanProject(@PathVariable Long id) {
////        LoanProject loanProject = loan.getLoanProjectById(id);
////        Float loanAmount = loanProject.getLoanamount();
////        List<Amortization> amortizationTable = serv.generateAmortizationT(loanAmount, loanProject);
////        loanProject.setAmortizationTable(amortizationTable);
////        loan.update(loanProject);
////        return amortizationTable;
////    }
//
//
