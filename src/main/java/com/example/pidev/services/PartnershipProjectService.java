package com.example.pidev.services;

import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@AllArgsConstructor
public class PartnershipProjectService implements IPartnershipProject{
PartnershipProjectRepository partnershipProjectRepository;
    @Override
    public List<PartnershipProject> retrieveAllPartnershipProjects() {
        return partnershipProjectRepository.findAll();
    }

    @Override
    public PartnershipProject addPartnershipProject(PartnershipProject p) {
        return partnershipProjectRepository.save(p);
    }

    @Override
    public PartnershipProject updatePartnershipProject(PartnershipProject p) {
        return partnershipProjectRepository.save(p);
    }

    @Override
    public PartnershipProject retrievePartnershipProject(Long idPartnership) {
        return partnershipProjectRepository.findById(idPartnership).get();
    }

    @Override
    public void deletePartnershipProject(Long idPartnership) {
        partnershipProjectRepository.deleteById(idPartnership);

    }
}
