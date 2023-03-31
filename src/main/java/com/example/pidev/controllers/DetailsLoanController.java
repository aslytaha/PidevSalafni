package com.example.pidev.controllers;


import com.example.pidev.Entities.DetailsLoans;
import com.example.pidev.services.DetailsLoansServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

@RequestMapping("/Details")
public class DetailsLoanController {

    DetailsLoansServiceImpl details;

    @GetMapping("/getdetail/idproj")
    public List<DetailsLoans> getProjects() {
        List<DetailsLoans> detailloan = details.getAllDetails();
        return detailloan;
    }


//    @PutMapping("/updateDetails")
  //  public DetailsLoans update(@RequestBody DetailsLoans d) {
    //    DetailsLoans detailsloan= details.updateD(d);
      //  return detailsloan;
    //}

   // @GetMapping("/retrieve-details/{details-id}")
    //public DetailsLoans retrievedetail(@PathVariable("DetailsLoans-details-id") Integer idDetails) {
      //  return details.getDetailsLoansById(idDetails);
    //}
}