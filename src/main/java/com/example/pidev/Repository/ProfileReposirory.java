package com.example.pidev.Repository;

import com.example.pidev.Entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileReposirory extends JpaRepository<Profile,Long> {
}
