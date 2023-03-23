package com.example.pidev.Repositories;

import com.example.pidev.Entities.PartnershipProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartnershipProjectRepository extends JpaRepository<PartnershipProject,Long> {


    @Query("SELECT p FROM PartnershipProject p LEFT JOIN FETCH p.requestPartnerships")
    List<PartnershipProject> findAllWithRequests();

}
