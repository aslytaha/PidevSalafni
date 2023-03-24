package com.example.pidev.services;

import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import com.example.pidev.Repositories.RequestPartnershipRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
@AllArgsConstructor
public class RequestPartnershipService implements IRequestPartnership{
    RequestPartnershipRepository requestPartnershipRepository;
    PartnershipProjectRepository partnershipProjectRepository;

    @Override
    public List<RequestPartnership> retrieveAllRequestPartnership() {
        return requestPartnershipRepository.findAll();
    }

    @Override
    public RequestPartnership addRequestPartnership(RequestPartnership r) {
        return requestPartnershipRepository.save(r);
    }

    @Override
    public RequestPartnership updateRequestPartnership(RequestPartnership r) {
        return requestPartnershipRepository.save(r);
    }

    @Override
    public RequestPartnership retrieveRequestPartnership(Long idRequest) {
        return requestPartnershipRepository.findById(idRequest).get();
    }

    @Override
    public void deleteRequestPartnership(Long idRequest) {
        requestPartnershipRepository.deleteById(idRequest);

    }


    public void addRequestAndAssignToProject(RequestPartnership request, Long projectId) {
        // récupérer le projet à partir de l'ID
        PartnershipProject project = partnershipProjectRepository.findById(projectId).orElse(null);

        if (project != null && project.getAmountRequested() != 0 && request.getAmountPayed() <= project.getAmountRequested()) {


            // assigner le projet à la demande de partenariat
            request.setPartnershipProjects(project);
            // ajouter la demande de partenariat au projet
            project.getRequestPartnerships().add(request);
            //Màj de amountRequested
            Long amountRequested = project.getAmountRequested() - request.getAmountPayed();
            project.setAmountRequested(amountRequested);

            float winPercentage = ((float) request.getAmountPayed() / (float) project.getAmountTotal()) * 100;
            request.setWinPercentage(winPercentage);
            // enregistrer les changements dans la base de données
            partnershipProjectRepository.save(project);
            requestPartnershipRepository.save(request);
            System.out.println("La demande de partenariat a été ajoutée et assignée au projet avec succès.");
        } else {
            System.out.println("Impossible d'assigner la demande de partenariat au projet. Vérifiez l'ID du projet fourni.");
        }
    }
}
