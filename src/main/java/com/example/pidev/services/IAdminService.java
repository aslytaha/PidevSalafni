package com.example.pidev.services;

import com.example.pidev.Entities.Admin;

import java.util.List;
import java.util.Optional;

public interface IAdminService {
    public Admin addAdmin(Admin a);
    public Admin updateAdmin(Admin a);
    public List<Admin> getAllAdmin();
    public Optional<Admin> getAdminById(Integer adminId);
    public void deleteAdmin(Integer adminId);

}
