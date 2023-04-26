package com.example.pidev.Interfaces;

import com.example.pidev.Entities.claim;

import java.io.IOException;
import java.util.List;

public interface IClaimServices {

    public List<claim> getallclaims();
    claim addOrUpdateClaim(claim c);
    void removeClaim (Integer id_claim);
    public claim Addclaim(claim claim) throws IOException;



}
