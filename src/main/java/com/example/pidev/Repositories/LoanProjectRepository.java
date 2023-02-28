package com.example.pidev.Repositories;

import com.example.pidev.Entities.LoanProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanProjectRepository extends JpaRepository <LoanProject,Long> {

}