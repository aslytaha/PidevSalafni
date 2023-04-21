package com.example.pidev.Repository;

import com.example.pidev.Entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response,Integer> {
}