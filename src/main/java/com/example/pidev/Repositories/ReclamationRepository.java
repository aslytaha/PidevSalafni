package com.example.pidev.Repositories;

import com.example.pidev.Entities.Reclamation;
import com.example.pidev.Enumerations.StatusTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {


    List<Reclamation> findByStatus(StatusTransaction status);
}