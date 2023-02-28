package Services;

import com.example.pidev.Entities.SafeGuard;

import java.util.List;

public interface Isafeguard {
    List<SafeGuard> getAllSafeGuards();

    SafeGuard getSafeGuardById(Long id);

    SafeGuard saveSafeGuard(SafeGuard safeGuard);

    void deleteSafeGuardById(Long id);
}
