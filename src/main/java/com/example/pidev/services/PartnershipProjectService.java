package com.example.pidev.services;

import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Entities.Statu;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import com.example.pidev.Repositories.RequestPartnershipRepository;
import com.example.pidev.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class PartnershipProjectService implements IPartnershipProject{
     @Autowired
    PartnershipProjectRepository partnershipProjectRepository;
      @Autowired
     RequestPartnershipRepository requestPartnershipRepository;
      @Autowired
      RequestPartnershipService requestPartnershipService;
      @Autowired
    UserRepository userRepository;
     @Autowired
    private EmailService emailService;
     @Autowired
     SMSClient smsClient;

    @Override
    public List<PartnershipProject> retrieveAllPartnershipProjects() {
        return partnershipProjectRepository.findAll();
    }

    @Override
    public List<PartnershipProject> retrieveAllPartnershipProjectsByUser(Long iduser) {
        return partnershipProjectRepository.findPartnershipProjectByUserId (iduser);
    }

    @Override
    public PartnershipProject addPartnershipProject(PartnershipProject p) {

        float shareofProject = ((float) p.getAmountRequested() / (float) p.getAmountTotal()) * 100;
        p.setShareofProject(shareofProject);
      //  notifyUsersOfNewProjects(p, "psssst nouveau projet" + p.getProjectName(),"fidele");
        return partnershipProjectRepository.save(p);
    }


    @Override
    public PartnershipProject retrievePartnershipProject(Long idPartnership) {
        return partnershipProjectRepository.findById(idPartnership).get();
    }

    @Override
    public void deletePartnershipProject(Long idPartnership) {
        partnershipProjectRepository.deleteById(idPartnership);

    }

    public List<PartnershipProject> getProjectsSortedByShareOfProject() {
        List<PartnershipProject> projects = partnershipProjectRepository.findAllByOrderByShareofProjectDesc();
        return projects;
    }

    public List<PartnershipProject> getProjectsByActivityArea(String activityArea) {
        List<PartnershipProject> projects = partnershipProjectRepository.findByActivityArea(activityArea);
        return projects;
    }




    public List<PartnershipProject> findBestProjects(double investmentAmount) {
        List<PartnershipProject> projects = partnershipProjectRepository.findAll();
        Map<PartnershipProject, Double> projectShareValues = new HashMap<>();
        List<PartnershipProject> bestProjects = new ArrayList<>();

        for (PartnershipProject project : projects) {
            if (investmentAmount >= 0.2 * project.getAmountTotal() && investmentAmount<= project.getAmountRequested() ) {
                long projectDuration = ChronoUnit.DAYS.between(project.getStartDate().toInstant(), project.getFinishDate().toInstant());
                double win=investmentAmount/project.getAmountTotal();
                double projectShareValue = win / projectDuration;

                projectShareValues.put(project, projectShareValue); // store the projectShareValue in the map
            }
        }

        // sort the projectShareValues map based on the projectShareValue
        Map<PartnershipProject, Double> sortedProjectShareValues = new LinkedHashMap<>();
        projectShareValues.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(entry -> sortedProjectShareValues.put(entry.getKey(), entry.getValue()));

        // iterate the sorted projectShareValues map and add the corresponding projects to the bestProjects list
        sortedProjectShareValues.forEach((project, projectShareValue) -> bestProjects.add(project));

        return bestProjects;
    }





    public void validerProject(Long projectId) {

        PartnershipProject project = partnershipProjectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        // Calculer le nombre de points du projet
        int points = calculateProjectPoints(project);

        // Mettre à jour le statut du projet
        if (points < 4) {
            project.setStatu(Statu.refusé);
        } else {
            project.setStatu(Statu.accepté);
            sendEmailToClient(projectId, "Votre projet a été validé.","hhhhh");
            notifyUsersOfNewProjects(project,"fidele Salafni","psssst nouveau projet" + project.getProjectName() + project.getShareofProject() +project.getDescriptionProject()+"ne ratez pas cette chance");
        }
        Long user = project.getUser().getPhone();
        smsClient.SendSMS(user.toString());

        partnershipProjectRepository.save(project);
    }

    private int calculateProjectPoints(PartnershipProject project) {
        // Calculer le pourcentage de financement actuel
        double fundingPercentage = ((double)project.getAmountRequested()/project.getAmountTotal()) * 100;

        long projectDuration = ChronoUnit.DAYS.between(project.getStartDate().toInstant(), project.getFinishDate().toInstant());

        // Assigner des points en fonction des mesures obtenues
        int points = 0;
        if (fundingPercentage >= 90) {
            points += 0;
        } else if (fundingPercentage >= 70) {
            points += 1;
        } else if (fundingPercentage >= 50) {
            points += 2;
        }else if (fundingPercentage >= 30) {
            points += 3;
        }else if (fundingPercentage >= 1) {
            points += 4;
        }

        if (projectDuration >= 365) {
            points += 0;
        } else if (projectDuration >= 270) {
            points += 1;
        } else if (projectDuration >= 180) {
            points += 2;
        }else if (projectDuration >= 90) {
            points += 3;
        }else if (projectDuration >= 1) {
            points += 4;
        }

        return points;
    }


   // @Scheduled(cron = "*/15 * * * * *")
    public PartnershipProject BestProject() {
        List<PartnershipProject> projects = partnershipProjectRepository.findAll();
        PartnershipProject bestProject = null;
        int bestScore = 4;
        for (PartnershipProject project : projects) {
            int score = calculateProjectPoints(project);
            if (score > bestScore) {
                bestProject = project;
                bestScore = score;
            }
        }
        return bestProject;
    }



    public void sendEmailToClient(Long projectId, String message,String subject) {
        PartnershipProject project = partnershipProjectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid project ID"));

        User user = project.getUser();

        emailService.sendEmail(user.getEmail(),subject, message);
    }


    public List<User> getAllUsersWithRequests() {
        List<RequestPartnership> requestPartnerships = requestPartnershipRepository.findAll();
        Set<User> users = new HashSet<>();
        for (RequestPartnership requestPartnership : requestPartnerships) {
            Integer IdClient=requestPartnership.getClientaccount().getIDClient();
            User user=userRepository.findUserByClientaccount(IdClient);
            users.add(user);
        }
        return new ArrayList<>(users);
    }

    public void notifyUsersOfNewProjects(PartnershipProject p,String subject,String text) {

        List<User> users = getAllUsersWithRequests();

        for (User user : users) {
            emailService.sendEmail(user.getEmail(), subject, text);
        }
    }


}
