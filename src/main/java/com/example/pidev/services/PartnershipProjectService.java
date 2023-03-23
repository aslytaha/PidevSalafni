package com.example.pidev.services;

import com.example.pidev.Entities.PartnershipProject;
import com.example.pidev.Repositories.PartnershipProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.temporal.ChronoUnit;
import java.util.*;

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


/*
    @Override
    public PartnershipProject findBestProject(double investmentAmount) {

        List<PartnershipProject> projects = partnershipProjectRepository.findAll();
        PartnershipProject bestProject = null;
        double bestValue = 0.0;
        for (PartnershipProject project : projects) {
            if (investmentAmount >= 0.2 * project.getAmountTotal()) {
                long projectDuration = ChronoUnit.DAYS.between(project.getStartDate().toInstant(), project.getFinishDate().toInstant());
                double projectShareValue = project.getShareofProject() / projectDuration;
                if (projectShareValue > bestValue) {
                    bestProject = project;
                    bestValue = projectShareValue;
                }
            }
        }
        return bestProject;
    }

*/
/*@Override
    public List<PartnershipProject> findBestProjects(double investmentAmount) {
        List<PartnershipProject> projects = partnershipProjectRepository.findAll();
        List<PartnershipProject> bestProjects = new ArrayList<>();
        double bestValue = 0.0;
        for (PartnershipProject project : projects) {
            if (investmentAmount >= 0.2 * project.getAmountTotal()) {
                long projectDuration = ChronoUnit.DAYS.between(project.getStartDate().toInstant(), project.getFinishDate().toInstant());
                double projectShareValue = project.getShareofProject() / projectDuration;
                if (projectShareValue > bestValue) {
                    bestProjects.clear();
                    bestProjects.add(project);
                    bestValue = projectShareValue;
                } else if (projectShareValue == bestValue) {
                    bestProjects.add(project);
                }
            }
        }
        return bestProjects;
    }

 */
    /*
public List<PartnershipProject> findBestProjects(double investmentAmount) {
    List<PartnershipProject> projects = partnershipProjectRepository.findAll();
    List<PartnershipProject> bestProjects = new ArrayList<>();
    double bestValue = 0.0;
    for (PartnershipProject project : projects) {
        if (investmentAmount >= 0.2 * project.getAmountTotal()) {
            long projectDuration = ChronoUnit.DAYS.between(project.getStartDate().toInstant(), project.getFinishDate().toInstant());
            double projectShareValue = project.getShareofProject() / projectDuration;
            if (projectShareValue >= bestValue) {
                if (projectShareValue > bestValue) {
                    bestProjects.clear();
                    bestValue = projectShareValue;
                }
                bestProjects.add(project);
            }
        }
    }
    // trier les projets par ordre décroissant de la valeur projectShareValue
    bestProjects.sort((p1, p2) -> Double.compare(p2.getShareofProject() / ChronoUnit.DAYS.between(p2.getStartDate().toInstant(), p2.getFinishDate().toInstant()),
            p1.getShareofProject() / ChronoUnit.DAYS.between(p1.getStartDate().toInstant(), p1.getFinishDate().toInstant())));
    return bestProjects;
}

     */
    /*
public List<PartnershipProject> findBestProjects(double investmentAmount) {
    List<PartnershipProject> projects = partnershipProjectRepository.findAll();
    List<PartnershipProject> bestProjects = new ArrayList<>();

    // Calculate projectShareValue for each project and add to a TreeMap for sorting
    TreeMap<Double, PartnershipProject> sortedProjects = new TreeMap<>(Collections.reverseOrder());
    for (PartnershipProject project : projects) {
        if (investmentAmount >= 0.2 * project.getAmountTotal()) {
            long projectDuration = ChronoUnit.DAYS.between(project.getFinishDate().toInstant(),project.getStartDate().toInstant());
            double projectShareValue = project.getShareofProject() / projectDuration;
            sortedProjects.put(projectShareValue, project);
        }
    }

    // Add the projects to the bestProjects list in descending order of projectShareValue0

    for (Map.Entry<Double, PartnershipProject> entry : sortedProjects.entrySet()) {
        bestProjects.add(entry.getValue());
    }

    return bestProjects;
}

     */

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




    public List<PartnershipProject> getAllProjectsWithRequests() {
        return partnershipProjectRepository.findAllWithRequests();
    }



}
