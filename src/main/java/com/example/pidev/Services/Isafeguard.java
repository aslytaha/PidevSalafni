package com.example.pidev.Services;

import com.example.pidev.Entities.SafeGuard;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Isafeguard {
    List<SafeGuard> getAllSafeGuards();

    SafeGuard getSafeGuardById(Long id);

    SafeGuard saveSafeGuard(SafeGuard safeGuard);

    void deleteSafeGuardById(Long id);
}
