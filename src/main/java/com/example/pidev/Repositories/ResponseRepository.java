package com.example.pidev.Repositories;

import com.example.pidev.DTO.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response,Integer> {
}