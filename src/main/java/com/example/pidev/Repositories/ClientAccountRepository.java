package com.example.pidev.Repositories;

import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientAccountRepository extends JpaRepository<ClientAccount,Integer> {
}
