package com.example.pidev.Service.Classes;
import com.example.pidev.Service.Interface.IClientAccount;
import com.example.pidev.Repository.ClientAccountRepository;
import com.example.pidev.Entities.ClientAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@AllArgsConstructor
@Service



public class ClientAccountService implements IClientAccount {
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
        return (List<ClientAccount>) clientaccountRepository.findAll();
    }
    @Override
    public ClientAccount getClientAccountById(Integer IDClient) {
        return clientaccountRepository.findById(IDClient).get();
    }
    @Override
    public List<ClientAccount> selectAll() {
        return clientaccountRepository.findAll();
    }
}
