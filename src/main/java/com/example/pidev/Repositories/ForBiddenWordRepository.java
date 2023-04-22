package com.example.pidev.Repositories;

import com.example.pidev.Entities.ForbiddenWord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForBiddenWordRepository extends JpaRepository<ForbiddenWord, Long> {
}

