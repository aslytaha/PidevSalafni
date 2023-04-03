package com.example.pidev.controllers;

import com.example.pidev.Entities.Amortization;
import com.example.pidev.Entities.Assurance;
import com.example.pidev.services.AmortizationService;
import com.example.pidev.services.ImpayedLoansService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/amortization")
public class AmortizationController{

@Autowired
AmortizationService amortizationService;
@Autowired
ImpayedLoansService impayedLoansService;




    @PostMapping("/add")
    public Amortization addamortization(@RequestBody Amortization a){
        return amortizationService.addAmortization(a);
    }


    @GetMapping("/getAll")
    public List<Amortization> getAllAmortization(){
        return amortizationService.getAllAmotization();
    }

}
