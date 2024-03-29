package com.example.pidev.Controllers;

import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.UserRepository;

<<<<<<< Updated upstream

import com.example.pidev.Services.ClientAccountService;
import com.lowagie.text.DocumentException;
=======
import com.example.pidev.Services.ClientAccountService;
>>>>>>> Stashed changes
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<<<<<<< Updated upstream
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
=======
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
>>>>>>> Stashed changes




@RestController
@RequestMapping("/clientaccounts")
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountController {
    @Autowired
    ClientAccountRepository clientAccountRepository;
    @Autowired
    ClientAccountService clientAccountService ;
    @Autowired
    UserRepository userRepository ;

    @PutMapping("/update")
    public ClientAccount updateClientAccount(@RequestBody ClientAccount c) {
        return clientAccountService.updateClientAccount(c);
    }


    @DeleteMapping("/delete/{IDClient}")

    public void deleteClientAccount(@PathVariable("IDClient") Integer IDClient)
    {
        clientAccountService.deleteClientAccount(IDClient);
    }


    @GetMapping("/getAll")
    public List<ClientAccount> getAllClientAccount(){
        return clientAccountService.getAllClientAccount();
    }



    @GetMapping("/Utilisateur/{IDClient}")
    public User UserbyClientAccount(@PathVariable("IDClient") Integer IDClient){
<<<<<<< Updated upstream
        return userRepository.findUserByClientaccount(Long.valueOf(IDClient));
=======
        return userRepository.findUserByClientaccount(IDClient);
>>>>>>> Stashed changes
    }
    @GetMapping("/getByID/{IDClient}")
    public ClientAccount getByClientAccount(@PathVariable("IDClient") Integer IDClient)
    {
        return clientAccountService.getClientAccountById(IDClient);
    }




<<<<<<< Updated upstream
    @GetMapping("/export")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd_HH:mm");
        String currentDateTime = dateFormater.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "Attachement;filename=inves_"+ currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        List<ClientAccount> listclientAccount =clientAccountService.getAllClientAccount();
        ClientAccountPDFExporter exporter = new ClientAccountPDFExporter(listclientAccount);
        exporter.export(response);
    }
=======

>>>>>>> Stashed changes




        //// statistique des comptes bancaires selon le solde /////

        @GetMapping("/clients/{Solde}")
        public List<ClientAccount> getClientsBySolde(@PathVariable("Solde") Float Solde) {
List<ClientAccount> cli=new ArrayList<>();
            List<ClientAccount> clients = clientAccountRepository.findAll();
            for (ClientAccount client : clients) {
                if (client.getSolde() > 0 && Solde > 0) {
                    cli.add(client);

                } else if (client.getSolde() < 0 && Solde < 0) {
                    cli.add(client);
                }


            }return cli;
        }






        /////// statistique des comptes bancaires selon l'age //////
        List<ClientAccount> clients= new ArrayList<>();
    @GetMapping("/age-stats/{ageGroup}")
    public Map<String, Integer> getAgeStats(@PathVariable("ageGroup") String ageGroup) {
        Map<String, Integer> stats = new HashMap<>();

       List <ClientAccount> clients=clientAccountService.getAllClientAccount();
        // définir les limites d'âge en fonction du groupe d'âge
        int minAge = -1;
        int maxAge = -1;
        switch (ageGroup) {
            case "18-35":
                minAge = 18;
                maxAge = 35;
                break;
            case "35-60":
                minAge = 35;
                maxAge = 60;
                break;

            case "less-than-0":
                minAge = Integer.MIN_VALUE;
                maxAge = 0;
                break;
            default:
                throw new IllegalArgumentException("Invalid age group: " + ageGroup);
        }


        // compter le nombre de clients dans la plage d'âge donnée
        int finalMinAge = minAge;
        int finalMaxAge = maxAge;
        int count = 0;
        for (ClientAccount account : clients) {
<<<<<<< Updated upstream
            User user=userRepository.findUserByClientaccount(Long.valueOf(account.getIDClient()));
=======
            User user=userRepository.findUserByClientaccount(account.getIDClient());
>>>>>>> Stashed changes
            if ((user.getAge() >= finalMinAge )&& (user.getAge() <= finalMaxAge) ){
                count++;
            }
        }

        // ajouter les statistiques à la carte
        stats.put("count", count);
        return stats;
    }

    }

