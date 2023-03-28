package com.example.pidev.Repositories;

import com.example.pidev.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByRoleName(String roleName);
}
