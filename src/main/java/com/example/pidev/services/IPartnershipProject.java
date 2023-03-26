package com.example.pidev.services;


import com.example.pidev.Entities.PartnershipProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IPartnershipProject {

    List<PartnershipProject> retrieveAllPartnershipProjects();

    PartnershipProject addPartnershipProject(PartnershipProject p);

    PartnershipProject updatePartnershipProject (PartnershipProject p);

    PartnershipProject retrievePartnershipProject (Long idPartnership);

    void deletePartnershipProject( Long idPartnership);

     List<PartnershipProject> getProjectsSortedByShareOfProject();
    List<PartnershipProject> getProjectsByActivityArea(String activityArea);

    List<PartnershipProject> findBestProjects(double investmentAmount);

    public void validerProject(Long projectId);





}
