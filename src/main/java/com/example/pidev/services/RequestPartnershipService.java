package com.example.pidev.services;

import com.example.pidev.Entities.*;
import com.example.pidev.Repositories.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RequestPartnershipService implements IRequestPartnership{
    @Autowired
    RequestPartnershipRepository requestPartnershipRepository;
    @Autowired
    PartnershipProjectRepository partnershipProjectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    ClientAccountRepository clientAccountRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;
    @Override
    public List<RequestPartnership> retrieveAllRequestPartnership() {
        return requestPartnershipRepository.findAll();
    }
@Autowired
SMSClient smsClient;
    @Override
    public RequestPartnership retrieveRequestPartnership(Long idRequest) {
        return requestPartnershipRepository.findById(idRequest).get();
    }


    public RequestPartnership addRequestAndAssignToProject(RequestPartnership request, Long projectId) {
        // récupérer le projet à partir de l'ID
        PartnershipProject project = partnershipProjectRepository.findById(projectId).get();

        if (project != null && project.getAmountRequested() != 0 && request.getAmountPayed() <= project.getAmountRequested() && project.getStatu().equals(Statu.accepté)) {


            // assigner le projet à la demande de partenariat
            request.setPartnershipProjects(project);
            // ajouter la demande de partenariat au projet
            project.getRequestPartnerships().add(request);


            float winPercentage = ((float) request.getAmountPayed() / (float) project.getAmountTotal()) * 100;
            request.setWinPercentage(winPercentage);

            // enregistrer les changements dans la base de données
            partnershipProjectRepository.save(project);
            requestPartnershipRepository.save(request);
            System.out.println("La demande de partenariat a été ajoutée et assignée au projet avec succès.");
            //smsClient.SendSMSs(project.getUser().getPhone().toString());
        }
        //MAIL
        sendEmailToClients(request, "Votre demande a été enregistre avec succes. passe au paiment _\n voici notre RIB bancaire : 123456","demande de partenariat");
        //SMS
        // smsClient.SendSMSs(project.getUser().getPhone().toString());
        return request;
    }



    public List<RequestPartnership> sortPartnershipRequestsByAmountPayed(Long projectId) {
        // récupérer le projet par son ID
        PartnershipProject project = partnershipProjectRepository.findById(projectId)
                .orElseThrow(() -> new EntityNotFoundException("Le projet n'existe pas"));

        // trier les demandes de partenariat selon le montant payé
        List<RequestPartnership> sortedRequests = project.getRequestPartnerships().stream()
                .sorted(Comparator.comparing(RequestPartnership::getAmountPayed).reversed())
                .collect(Collectors.toList());

        return sortedRequests;
    }



    public void removeRequestAndAdjustAmount(Long requestId, Long projectId) {
        // récupérer le projet à partir de l'ID
        PartnershipProject project = partnershipProjectRepository.findById(projectId).orElse(null);
        RequestPartnership request = requestPartnershipRepository.findById(requestId).orElse(null);

        if (project != null && project.getRequestPartnerships().contains(request)) {
            // supprimer la demande de partenariat de la liste
            project.getRequestPartnerships().remove(request);
            //Màj de amountRequested
            Long amountRequested = project.getAmountRequested() + request.getAmountPayed();
            project.setAmountRequested(amountRequested);
            // enregistrer les changements dans la base de données
            partnershipProjectRepository.save(project);
            requestPartnershipRepository.delete(request);
            System.out.println("La demande de partenariat a été supprimée du projet avec succès.");
        } else {
            System.out.println("Impossible de supprimer la demande de partenariat du projet. Vérifiez l'ID du projet fourni.");
        }
    }


    public void sendEmailToClients(RequestPartnership request, String message,String subject) {
         ClientAccount clientAccount=request.getClientaccount();
         Integer IdClient=clientAccount.getIDClient();
         User users=userRepository.findUserByClientaccount(IdClient);

                 String Emailuser = users.getEmail();
                 emailService.sendEmail(Emailuser,subject, message);



    }



    public List<RequestPartnership> getBestRequest() {
        // Récupérer les 3 meilleures demandes de partenariat
        List<RequestPartnership> requests = requestPartnershipRepository.findAllByOrderByAmountPayedDesc().stream().limit(3).collect(Collectors.toList());

        // Parcourir les demandes et prolonger l'abonnement du client associé
        for (RequestPartnership request : requests) {
            ClientAccount clientaccount = request.getClientaccount();
            // Prolonger l'abonnement de 30 jours
            LocalDate currentDate = clientaccount.getExpirationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate newExpirationDate = currentDate.plusDays(30);
            clientaccount.setExpirationDate(Date.from(newExpirationDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            clientAccountRepository.save(clientaccount);
            // Envoi de l'email de notification
            User user = userRepository.findUserByClientaccount(clientaccount.getIDClient());
            String emailContent = "Votre abonnement a été prolongé suite à notre partenariat réussi.";
            emailService.sendEmail(user.getEmail(), "Prolongation d'abonnement", emailContent);
        }

        // Retourner les 3 meilleures demandes de partenariat
        return requests;
    }



   public PartnershipProject payement (Long idRequest , Long RIB)
   {

       BankAccount account = bankAccountRepository.findBankAccountByRIB(RIB);
       RequestPartnership request = requestPartnershipRepository.findById(idRequest).get();
       PartnershipProject project=request.getPartnershipProjects();

       ClientAccount clientAccount= request.getClientaccount();
      float montant= request.getAmountPayed();
      float m= clientAccount.getSolde() - montant;
      if (m>=0 && request.getAmountPayed() <= project.getAmountRequested() && project.getStatu().equals(Statu.accepté) ) {
          clientAccount.setSolde((int) m);
          clientAccountRepository.save(clientAccount);
          float c = account.getSolde() + montant;
          account.setSolde(c);
          bankAccountRepository.save(account);
          request.setStatu(Statu.payed);

          Long amountRequested = project.getAmountRequested() - request.getAmountPayed();
          project.setAmountRequested(amountRequested);
          partnershipProjectRepository.save(project);
          sendEmailToClients(request, "Votre demande a été validé. paiement avec succee","demande de partenariat");
          smsClient.SendSMSs(project.getUser().getPhone().toString());
      }

       return project;
   }





}
