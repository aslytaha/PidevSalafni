package com.example.pidev.Repositories;

import com.example.pidev.Entities.Profile;
import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileReposirory extends JpaRepository<Profile,Long> {
}
