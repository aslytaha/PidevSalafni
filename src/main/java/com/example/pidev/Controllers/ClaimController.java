package com.example.pidev.Controllers;

import com.example.pidev.Entities.claim;
import com.example.pidev.Interfaces.IClaimServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/claim")
public class ClaimController {

    private final IClaimServices claimServices;




    @PostMapping("/add")
    claim addClaim(@RequestBody claim claim ){
        return claimServices.addOrUpdateClaim(claim);
    }

    @GetMapping("/getallclaims/")
    List<claim> getallclaims(){
        return claimServices.getallclaims();
    }

   

    @DeleteMapping("/delete/{id}")
    void deleteClaim(@PathVariable("id") Integer id_claim){
        claimServices.removeClaim(id_claim);
    }


    @PostMapping("/claims")
    public ResponseEntity<claim> Addclaim(@RequestBody claim claim) {
        try {
            claim newclaim = claimServices.Addclaim(claim);
            return new ResponseEntity<>(newclaim, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
