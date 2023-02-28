package com.example.pidev.services;


import com.example.pidev.Entities.PartnershipProject;

import java.util.List;

public interface IPartnershipProject {

    List<PartnershipProject> retrieveAllPartnershipProjects();

    PartnershipProject addPartnershipProject(PartnershipProject p);

    PartnershipProject updatePartnershipProject (PartnershipProject p);

    PartnershipProject retrievePartnershipProject (Long idPartnership);

    void deletePartnershipProject( Long idPartnership);

}
