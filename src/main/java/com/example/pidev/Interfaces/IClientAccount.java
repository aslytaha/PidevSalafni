package com.example.pidev.Interfaces;

import com.example.pidev.Entities.ClientAccount;

import java.util.List;

public interface IClientAccount {


    ClientAccount updateClientAccount(ClientAccount c);
    void deleteClientAccount(Integer idClient);
    List<ClientAccount> getAllClientAccount();
    ClientAccount getClientAccountById(Integer idClient);

}
