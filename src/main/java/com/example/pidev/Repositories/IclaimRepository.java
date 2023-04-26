package com.example.pidev.Repositories;
import com.example.pidev.Entities.claim;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IclaimRepository extends JpaRepository<claim, Integer> {
}