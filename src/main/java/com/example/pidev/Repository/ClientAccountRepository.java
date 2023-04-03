package com.example.pidev.Repository;
import com.example.pidev.Entities.ClientAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientAccountRepository  extends JpaRepository<ClientAccount, Integer> {
//    ClientAccount findClientAccountBySolde(float solde);


}
