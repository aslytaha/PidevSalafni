package com.example.pidev.services;

import com.example.pidev.Entities.Admin;
import com.example.pidev.Repositories.AdminRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements IAdminService{
AdminRepository adminRepository;
    @Override
    public Admin addAdmin(Admin a) {

        return adminRepository.save(a);
    }

    @Override
    public Admin updateAdmin(Admin a) {

        return adminRepository.save(a);
    }

    @Override
    public List<Admin> getAllAdmin() {

        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(Integer adminId) {

        return Optional.ofNullable(adminRepository.findById(adminId).orElse(null));
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        adminRepository.deleteById(adminId);
    }
}
