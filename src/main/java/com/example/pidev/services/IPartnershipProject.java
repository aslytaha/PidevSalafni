package com.example.pidev.services;


import com.example.pidev.Entities.PartnershipProject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IPartnershipProject {

    List<PartnershipProject> retrieveAllPartnershipProjects();

    PartnershipProject addPartnershipProject(PartnershipProject p);

    PartnershipProject updatePartnershipProject (PartnershipProject p);

    PartnershipProject retrievePartnershipProject (Long idPartnership);

    void deletePartnershipProject( Long idPartnership);

}
