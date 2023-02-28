package com.example.pidev.services;

import com.example.pidev.Entities.Assurance;
import com.example.pidev.Repositories.AssuranceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AssuranceService implements IAssuranceService{
    AssuranceRepository assuranceRepository;
    @Override
    public Assurance addAssurance(Assurance as) {
        return assuranceRepository.save(as);
    }

    @Override
    public Assurance updateAssurance(Assurance as) {
        return assuranceRepository.save(as);
    }

    @Override
    public List<Assurance> getAllAssurance() {
        return assuranceRepository.findAll();
    }

    @Override
    public Optional<Assurance> getAssuranceById(Long asId) {
        return Optional.ofNullable(assuranceRepository.findById(asId).orElse(null));
    }

    @Override
    public void deleteAssurance(Long asId) {
        assuranceRepository.deleteById(asId);
    }
}
