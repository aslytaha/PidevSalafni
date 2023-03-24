package com.example.pidev.services;

import com.example.pidev.Entities.Act;
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








}
