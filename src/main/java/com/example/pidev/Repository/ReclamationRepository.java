package com.example.pidev.Repository;

import com.example.pidev.Entities.Reclamation;
import com.example.pidev.Entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {


    List<Reclamation> findByStatus(Status status);
}