package com.example.pidev.Controllers;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import com.example.pidev.Services.PartnershipProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/partnership")
public class PartnershipProjectController {
    @Autowired
    PartnershipProjectService partnershipProject;
    @Autowired

    PartnershipProjectRepository partnershipProjectRepository;

    // http://localhost:8089/salafni/partnership/retrieve-all-partnership
    @GetMapping("/retrieve-all-partnership")
    public List<PartnershipProject> getPartnerships() {
        List<PartnershipProject> listPartnerships = partnershipProject.retrieveAllPartnershipProjects();
        return listPartnerships;
    }


    @GetMapping("/all-partnership/{iduser}")
    public List<PartnershipProject> getPartnershipsByUser(@PathVariable ("iduser") Long iduser) {
        List<PartnershipProject> listPartnerships = partnershipProject.retrieveAllPartnershipProjectsByUser(iduser);
        return listPartnerships;
    }

    @GetMapping("/getProjectsSortedByShareOfProject")
    public List<PartnershipProject> getPartnershipsTrier(){
        List<PartnershipProject> lists= partnershipProject.getProjectsSortedByShareOfProject();
        return lists;
    }


    @GetMapping("/projects/search")
    public List<PartnershipProject> searchProjectsByActivityArea(@RequestParam("activityArea") String activityArea) {
        List<PartnershipProject> projects = partnershipProject.getProjectsByActivityArea(activityArea);
        return projects;
    }

    // http://localhost:8089/salafni/partnership/retrieve-partnership/{partnership-id}
    @GetMapping("/retrieve-partnership/{partnership-id}")
    public PartnershipProject retrievePartnership(@PathVariable("partnership-id") Long partnershipId) {
        return partnershipProject.retrievePartnershipProject(partnershipId);
    }

    // http://localhost:8089/salafni/partnership/add-partnership
    @PostMapping("/add-partnership")
    public PartnershipProject addPartnership(@RequestBody PartnershipProject p) {
        PartnershipProject partnership = partnershipProject.addPartnershipProject(p);
        return partnership;
    }

    // http://localhost:8089/salafni/partnership/remove-partnership/1
    @DeleteMapping("/remove-partnership/{partnership-id}")
    public void removePartnership(@PathVariable("partnership-id") Long partnershipId) {
        partnershipProject.deletePartnershipProject(partnershipId);
    }




    @GetMapping("/projects/best")
    public List<PartnershipProject> findBestProjects(@RequestParam double investmentAmount) {
       // List<PartnershipProject> projects = partnershipProjectRepository.findAll();
        List<PartnershipProject> bestProjects = partnershipProject.findBestProjects(investmentAmount);
        return bestProjects;
    }


    @PutMapping("/{projectId}/status")
    public Optional<PartnershipProject> updateProjectStatus(@PathVariable Long projectId) {

            partnershipProject.validerProject(projectId);
        Optional<PartnershipProject> project=partnershipProjectRepository.findById(projectId);
            return project;

    }



    @DeleteMapping("/remove-project/{partnership-id}/{RIB}")
    public ResponseEntity<String> removePartnershipProject(@PathVariable("partnership-id") Long partnershipId, @PathVariable("RIB") Long RIB ) {
        try {
            partnershipProject.projectNonFinancee(partnershipId,RIB);
            return ResponseEntity.ok("le projets suprimer!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("le projet n'est pas supprimer " + e.getMessage());
        }


    }



}
