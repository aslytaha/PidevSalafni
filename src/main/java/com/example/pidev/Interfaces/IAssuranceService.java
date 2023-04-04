package com.example.pidev.Interfaces;

import com.example.pidev.Entities.Assurance;

import java.util.List;

public interface IAssuranceService {
    public Assurance addAssurance(Assurance as);
    public Assurance updateAssurance(Assurance as);
    public List<Assurance> getAllAssurance();
    public Assurance getAssuranceById(Integer asID);
    public void deleteAssurance(Integer asID);
   // Assurance addAssuranceAndAssignToLoanProject(Assurance assurance, Long Idprojet);
}
