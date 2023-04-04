package com.example.pidev.Controllers;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import com.example.pidev.Repositories.RequestPartnershipRepository;

import com.example.pidev.Services.PartnershipProjectService;
import com.example.pidev.Services.RequestPartnershipService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/request")
public class RequestPartnershipController {


    @Autowired
    PartnershipProjectService partnershipProjectService;
    @Autowired
    RequestPartnershipService requestPartnershipService;
    @Autowired
    PartnershipProjectRepository partnershipProjectRepository;
    @Autowired
    RequestPartnershipRepository requestPartnershipRepository;



    // http://localhost:8089/salafni/request/retrieve-all-request
    @GetMapping("/retrieve-all-request")
    public List<RequestPartnership> getRequests() {
        List<RequestPartnership> listRequests = requestPartnershipService.retrieveAllRequestPartnership();
        return listRequests;
    }

    // http://localhost:8089/salafni/request/retrieve-request/2
    @GetMapping("/retrieve-request/{request-id}")
    public RequestPartnership retrieveRequest(@PathVariable("request-id") Long requestId) {
        return requestPartnershipService.retrieveRequestPartnership(requestId);
    }

    @PutMapping("/payement-request/{request-id}/{RIB}")
    public PartnershipProject payRequest(@PathVariable("request-id") Long requestId ,@PathVariable ("RIB") Long RIB) {
        PartnershipProject request = requestPartnershipService.payement(requestId,RIB);
        return request;
    }



    @PostMapping("/projects/{projectId}/requests")
    public RequestPartnership addRequestAndAssignToProject(@RequestBody RequestPartnership request, @PathVariable Long projectId) {
     RequestPartnership requestPartnership=   requestPartnershipService.addRequestAndAssignToProject(request, projectId);

        return requestPartnership;
    }


    @GetMapping("/projects/{projectId}/partnership-requests")
    public List<RequestPartnership> getSortedPartnershipRequests(@PathVariable Long projectId) {
        List<RequestPartnership> sortedRequests = requestPartnershipService.sortPartnershipRequestsByAmountPayed(projectId);
        return sortedRequests;
    }


    @DeleteMapping("/partnershipproject/{projectId}/requests/{requestId}")
    public ResponseEntity<String> removeRequestAndAdjustAmount(@PathVariable Long projectId, @PathVariable Long requestId) {
        try {
            requestPartnershipService.removeRequestAndAdjustAmount(requestId, projectId);
            return ResponseEntity.ok("La demande de partenariat a été supprimée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de la demande de partenariat.");
        }
    }



    @GetMapping("/partnership/best-requests")
    public ResponseEntity<List<RequestPartnership>> getBestRequests() {
        List<RequestPartnership> bestRequests = requestPartnershipService.getBestRequest();
        return ResponseEntity.ok(bestRequests);
    }



}
