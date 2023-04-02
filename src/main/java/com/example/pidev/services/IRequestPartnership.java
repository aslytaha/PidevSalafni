package com.example.pidev.services;


import com.example.pidev.Entities.RequestPartnership;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IRequestPartnership {

    List<RequestPartnership> retrieveAllRequestPartnership();


    RequestPartnership retrieveRequestPartnership (Long idRequest);



    RequestPartnership addRequestAndAssignToProject(RequestPartnership request, Long projectId);
    List<RequestPartnership> sortPartnershipRequestsByAmountPayed(Long projectId);
    void removeRequestAndAdjustAmount(Long requestId, Long projectId);
    void sendEmailToClients(RequestPartnership request, String message,String subject);

     List<RequestPartnership> getBestRequest();



}
