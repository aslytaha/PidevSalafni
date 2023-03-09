package com.example.pidev.Services;


import com.example.pidev.Entities.SafeGuard;
import com.example.pidev.Repositories.SafeGuardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public  class SafeGuardServiceImpl implements Isafeguard {

    @Autowired
    private SafeGuardRepository safeGuardRepository;

    @Override
    public List<SafeGuard> getAllSafeGuards() {
        return safeGuardRepository.findAll();
    }

    @Override
    public SafeGuard getSafeGuardById(Long id) {
        return safeGuardRepository.findById(id).orElse(null);
    }

    @Override
    public SafeGuard saveSafeGuard(SafeGuard safeGuard) {
        return safeGuardRepository.save(safeGuard);
    }

    @Override
    public void deleteSafeGuardById(Long id) {
        safeGuardRepository.deleteById(id);
    }
}
