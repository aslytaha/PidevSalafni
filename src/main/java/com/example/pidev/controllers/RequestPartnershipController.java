package com.example.pidev.controllers;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import com.example.pidev.services.IPartnershipProject;
import com.example.pidev.services.IRequestPartnership;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/request")
public class RequestPartnershipController {

    IRequestPartnership requestPartnership;
    IPartnershipProject partnershipProject;
    PartnershipProjectRepository partnershipProjectRepository;

    // http://localhost:8089/salafni/request/retrieve-all-request
    @GetMapping("/retrieve-all-request")
    public List<RequestPartnership> getRequests() {
        List<RequestPartnership> listRequests = requestPartnership.retrieveAllRequestPartnership();
        return listRequests;
    }

    // http://localhost:8089/salafni/request/retrieve-request/2
    @GetMapping("/retrieve-request/{request-id}")
    public RequestPartnership retrieveRequest(@PathVariable("request-id") Long requestId) {
        return requestPartnership.retrieveRequestPartnership(requestId);
    }

    // http://localhost:8089/salafni/request/add-request
    @PostMapping("/add-request")
    public RequestPartnership addRequest(@RequestBody RequestPartnership r) {
        RequestPartnership request = requestPartnership.addRequestPartnership(r);
        return request;
    }

    // http://localhost:8089/salafni/request/remove-request/1
    @DeleteMapping("/remove-request/{request-id}")
    public void removeRequest(@PathVariable("request-id") Long requestId) {
        requestPartnership.deleteRequestPartnership(requestId);
    }

    // http://localhost:8089/salafni/request/update-request
    @PutMapping("/update-request")
    public RequestPartnership updateRequest(@RequestBody RequestPartnership r) {
        RequestPartnership request= requestPartnership.updateRequestPartnership(r);
        return request;
    }


    @PostMapping("/projects/{projectId}/requests")
    public Optional<PartnershipProject> addRequestAndAssignToProject(@RequestBody RequestPartnership request, @PathVariable Long projectId) {
        requestPartnership.addRequestAndAssignToProject(request, projectId);
        Optional<PartnershipProject> p =partnershipProjectRepository.findById(projectId);
        return p;
    }


    @GetMapping("/projects/{projectId}/partnership-requests")
    public List<RequestPartnership> getSortedPartnershipRequests(@PathVariable Long projectId) {
        List<RequestPartnership> sortedRequests = requestPartnership.sortPartnershipRequestsByAmountPayed(projectId);
        return sortedRequests;
    }


    @DeleteMapping("/partnershipproject/{projectId}/requests/{requestId}")
    public ResponseEntity<String> removeRequestAndAdjustAmount(@PathVariable Long projectId, @PathVariable Long requestId) {
        try {
            requestPartnership.removeRequestAndAdjustAmount(requestId, projectId);
            return ResponseEntity.ok("La demande de partenariat a été supprimée avec succès.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de la demande de partenariat.");
        }
    }


}
