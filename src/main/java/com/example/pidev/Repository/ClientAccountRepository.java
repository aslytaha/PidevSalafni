package com.example.pidev.Repository;
import com.example.pidev.Entities.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientAccountRepository  extends JpaRepository<ClientAccount, Integer> {
}
