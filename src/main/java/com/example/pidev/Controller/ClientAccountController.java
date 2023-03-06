package com.example.pidev.Controller;
import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Service.Classes.ClientAccountService;
import com.example.pidev.Service.Interface.IClientAccount;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@RestController

public class ClientAccountController {
    @Autowired
    ClientAccountService clientAccountService ;


    @PostMapping("/add")
    public ClientAccount addClientAccount(@RequestBody ClientAccount c) {
        return clientAccountService .addClientAccount(c);
    }


    @PutMapping("/update")
    public ClientAccount updateClientAccount(@RequestBody ClientAccount c) {
        return clientAccountService .updateClientAccount(c);
    }


    @DeleteMapping("/delete/{IDClient}")

    public void deleteClientAccount(@PathVariable("IDClient") Integer IDClient)
    {
        clientAccountService .deleteClientAccount(IDClient);
    }


    @GetMapping("/getAll")
    public List<ClientAccount> getAllClientAccount(){
        return clientAccountService .getAllClientAccount();
    }


    @GetMapping("/getByID/{IDClient}")
    public ClientAccount getByClientAccount(@PathVariable("IDClient") Integer IDClient)
    {
        return clientAccountService .getClientAccountById(IDClient);
    }

}
