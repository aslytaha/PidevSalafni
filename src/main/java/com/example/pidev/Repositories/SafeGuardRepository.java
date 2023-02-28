package com.example.pidev.Repositories;

import com.example.pidev.Entities.SafeGuard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SafeGuardRepository extends JpaRepository<SafeGuard,Long> {

}
