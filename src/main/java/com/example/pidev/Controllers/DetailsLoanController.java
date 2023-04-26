package com.example.pidev.Controllers;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.Services.DetailsLoansServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/Details")
public class DetailsLoanController {

    DetailsLoansServiceImpl details;




    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
    }
    @GetMapping("/getdetail")
    public List<DetailsLoans> getdetail() {
        List<DetailsLoans> detailloan = details.getAllDetails();
        return detailloan;
    }




    @PutMapping("/updateDetails")
    public DetailsLoans updateDD(@RequestBody DetailsLoans d) {
        DetailsLoans detailsloan= details.updateD(d);
        return detailsloan;
    }

    @GetMapping("/retrieve-details/{details-id}")
    public DetailsLoans retrievedetail(@PathVariable("DetailsLoans-details-id") Integer idDetails) {
        return details.getDetailsLoansById(idDetails);
    }
}
