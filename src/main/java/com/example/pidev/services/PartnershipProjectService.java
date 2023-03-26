package com.example.pidev.services;

import com.example.pidev.Entities.Act;
import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Entities.RequestPartnership;
import com.example.pidev.Entities.Statu;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PartnershipProjectService implements IPartnershipProject{
PartnershipProjectRepository partnershipProjectRepository;
    @Override
    public List<PartnershipProject> retrieveAllPartnershipProjects() {
        return partnershipProjectRepository.findAll();
    }

    @Override
    public PartnershipProject addPartnershipProject(PartnershipProject p) {

        float shareofProject = ((float) p.getAmountRequested() / (float) p.getAmountTotal()) * 100;
        p.setShareofProject(shareofProject);
        return partnershipProjectRepository.save(p);
    }

    @Override
    public PartnershipProject updatePartnershipProject(PartnershipProject p) {
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
                double projectShareValue = project.getShareofProject() / projectDuration;

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
        if (points < 5) {
            project.setStatu(Statu.refusé);
        } else {
            project.setStatu(Statu.accepté);
        }

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





}
