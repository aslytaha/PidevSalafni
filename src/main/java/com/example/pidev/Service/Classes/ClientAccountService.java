package com.example.pidev.Service.Classes;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.UserRepository;
import com.example.pidev.Service.Interface.IClientAccount;
import com.example.pidev.Repository.ClientAccountRepository;
import com.example.pidev.Entities.ClientAccount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountService implements IClientAccount {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private ClientAccountRepository clientaccountRepository;


    @Override
    public ClientAccount updateClientAccount(ClientAccount c) {
        return clientaccountRepository.save(c);
    }
    @Override
    public void deleteClientAccount(Integer IDClient) {
        clientaccountRepository.deleteById(IDClient);
    }
    @Override
    public List<ClientAccount> getAllClientAccount() {

        return clientaccountRepository.findAll();
    }
    @Override
    public ClientAccount getClientAccountById(Integer IDClient) {
        return clientaccountRepository.findById(IDClient).get();
    }


    public User getUserbyClientAccount(Integer IDClient) {
        return userRepository.findUserByClientaccount(IDClient) ;
    }
}
