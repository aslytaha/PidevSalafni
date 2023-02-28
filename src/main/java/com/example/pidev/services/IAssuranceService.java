package com.example.pidev.services;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Entities.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IAssuranceService {
    public Assurance addAssurance(Assurance as);
    public Assurance updateAssurance(Assurance as);
    public List<Assurance> getAllAssurance();
    public Optional<Assurance> getAssuranceById(Long asId);
    public void deleteAssurance(Long asId);
}
