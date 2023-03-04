package com.example.pidev.controllers;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.services.IPartnershipProject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/partnership")
public class PartnershipProjectController {
    IPartnershipProject partnershipProject;

    // http://localhost:8089/salafni/partnership/retrieve-all-partnership
    @GetMapping("/retrieve-all-partnership")
    public List<PartnershipProject> getPartnerships() {
        List<PartnershipProject> listPartnerships = partnershipProject.retrieveAllPartnershipProjects();
        return listPartnerships;
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

    // http://localhost:8089/salafni/partnership/update-partnership
    @PutMapping("/update-partnership")
    public PartnershipProject updatePartnership(@RequestBody PartnershipProject p) {
        PartnershipProject partnership= partnershipProject.updatePartnershipProject(p);
        return partnership;
    }

}
