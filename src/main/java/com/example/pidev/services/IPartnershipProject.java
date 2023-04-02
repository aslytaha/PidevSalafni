package com.example.pidev.services;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IPartnershipProject {

    List<PartnershipProject> retrieveAllPartnershipProjects();

    PartnershipProject addPartnershipProject(PartnershipProject p);
    List<PartnershipProject> retrieveAllPartnershipProjectsByUser(Long iduser);

    PartnershipProject retrievePartnershipProject (Long idPartnership);

    void deletePartnershipProject( Long idPartnership);

     List<PartnershipProject> getProjectsSortedByShareOfProject();
    List<PartnershipProject> getProjectsByActivityArea(String activityArea);

    List<PartnershipProject> findBestProjects(double investmentAmount);

    public void validerProject(Long projectId);

    public PartnershipProject BestProject();
  //  void sendEmailToClient(Long projectId, String message,String subject);

     void notifyUsersOfNewProjects(PartnershipProject p,String subject,String text);
    List<User> getAllUsersWithRequests();





}
