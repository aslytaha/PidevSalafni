package com.example.pidev.controllers;


import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.services.IRequestPartnership;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/request")
public class RequestPartnershipController {

    IRequestPartnership requestPartnership;

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
    public ResponseEntity<String> addRequestAndAssignToProject(@RequestBody RequestPartnership request, @PathVariable Long projectId) {
        requestPartnership.addRequestAndAssignToProject(request, projectId);
        return ResponseEntity.ok("La demande de partenariat a été ajoutée et assignée au projet avec succès.");
    }

}
