package com.example.pidev.Repository;

import com.example.pidev.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Role findRoleByRoleName(String roleName);
}
