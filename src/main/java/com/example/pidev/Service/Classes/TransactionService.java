package com.example.pidev.Service.Classes;
import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Entities.TransactionState;
import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.UserRepository;
import com.example.pidev.Repository.ClientAccountRepository;
import com.example.pidev.Repository.TransactionRepository;
import com.example.pidev.Service.Interface.ITransaction;
import com.example.pidev.Entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@NoArgsConstructor
public class TransactionService implements ITransaction {

    @Autowired
    ClientAccountService clientAccountService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    private IEmailService emailService;
    private SMSService smsService;


    //    public User retrieveUser(ClientAccount clientAccount){
//        Integer account=clientAccount.getIDClient();
//
//        return null;
//    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Integer compteDestinataire, float montant, String type_transaction) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(compteDestinataire)
                .get();
        float solde = clientAccount.getSolde() + montant;
        clientAccount.setSolde(solde);

        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setCompteDestinataire(clientAccount);
        transaction.setAmount(montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
//        sendEmailToClient(compteDestinataire, "belha miboun","depot trans");
//
//       User u = clientAccountService.getUserbyClientAccount(compteDestinataire);
//       String to = u.getEmail();
//       String subject = "Transaction validation code";
//
//       String code = String.valueOf(11111);    //String code =     //UUID.randomUUID().toString().substring(0, 6);
//       String text = "Your transaction validation code is: " + code;
//        emailService.sendEmail(to,subject,text);


//        smsService.sendSMS(String.valueOf(u.getPhone()),code);
//      transaction.setValidationCode(code); // Save validation code in transaction
        transactionRepository.save(transaction);


    }


//    public void sendEmailToClient(Integer IdClient,String message,String subject) {
//
//   User clientAccount= userRepository.findUserByClientaccount(IdClient);
//
//        emailService.sendEmail(clientAccount.getEmail(),subject, message);
//    }


//
//    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
//    public void validerTransaction(Integer transactionId, String codeValidation) throws Exception {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new Exception("Transaction non trouvée"));
//        if (!transaction.getValidationCode().equals(codeValidation) /*|| transaction.getDate().plusDays(1).isBefore(LocalDateTime.now())*/) { //Vérifier si la transaction est en attente de validation
//            transaction.setEtat(TransactionState.CANCELED);
//            //   transactionRepository.save(transaction);
//            throw new Exception("La transaction a été annulée car le code de validation est incorrect ou le temps de validation a dépassé 1 jour");
//        }
//        if (!transaction.getValidationCode().equals(codeValidation)) { //Vérifier si le code de validation est correct
//            throw new Exception("Le code de validation est incorrect");
//        }
//        transaction.setEtat(TransactionState.VALIDATED); // Mettre à jour l'état de la transaction en "Transaction is validated"
//        transactionRepository.save(transaction);
//
//
//    }
//
//

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Integer compteDestinataire, float montant, String type_transaction) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() - montant;
        if (montant > clientAccount.getSolde()) {
            throw new Exception("Solde insuffisant");
        }
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setCompteEmetteur(clientAccount);
        transaction.setAmount(-montant);
        transaction.setDate(LocalDateTime.now());
        transaction.setType_transaction(type_transaction);
        transaction.setEtat(TransactionState.PENDING);
//        Integer IdClient=clientAccount.getIDClient();
//        User users=userRepository.findUserByClientaccount(IdClient);
//
//        String Emailuser = users.getEmail();
//        String to = users.getEmail();
//        String subject = "Transaction validation code";
//
        String code = new SecureRandom().ints(6, 0, 36)
                .mapToObj(i -> Integer.toString(i, 36))
                .collect(Collectors.joining())
                .toUpperCase();
//        String text = "Your transaction validation code is: " + code;
//        emailService.sendEmail(to, subject, text);
//        smsService.sendSMS(String.valueOf(users.getPhone()),code);

        transaction.setValidationCode(code); // Save validation code in transaction

        transactionRepository.save(transaction);
    }


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void transfert(Integer compteEmetteur, Integer compteDestinataire, float montant, String type_transaction) throws Exception {
        ClientAccount compteEmmetteur = clientAccountRepository.findById(compteEmetteur).orElseThrow(() -> new Exception("Compte source non trouvé"));
        ClientAccount compteDesstinataire = clientAccountRepository.findById(compteDestinataire).orElseThrow(() -> new Exception("Compte destination non trouvé"));
        float soldeSrc = compteEmmetteur.getSolde() - montant;
        if (compteEmmetteur.getSolde() < montant) {
            throw new Exception("Solde insuffisant");
        }
        compteEmmetteur.setSolde(soldeSrc);
        clientAccountRepository.save(compteEmmetteur);

        float soldeDest = compteDesstinataire.getSolde() + montant;

        compteDesstinataire.setSolde(soldeDest);

        clientAccountRepository.save(compteDesstinataire);

        Transaction transactionSrc = new Transaction();
        transactionSrc.setCompteEmetteur(compteEmmetteur);
        transactionSrc.setAmount(-montant);
        transactionSrc.setDate(LocalDateTime.now());
        transactionSrc.setType_transaction(type_transaction);
        transactionSrc.setEtat(TransactionState.PENDING);
        transactionRepository.save(transactionSrc);

        Transaction transactionDest = new Transaction();
        transactionDest.setCompteDestinataire(compteDesstinataire);
        transactionDest.setAmount(montant);
        transactionDest.setDate(LocalDateTime.now());
        transactionDest.setType_transaction(type_transaction);
         transactionDest.setEtat(transactionSrc.getEtat());
        transactionRepository.save(transactionDest);
//        Integer IdEmmeteur=compteEmmetteur.getIDClient();
//        User userEmmeteur=userRepository.findUserByClientaccount(IdEmmeteur);
//
//
//        String to = userEmmeteur.getEmail();// getClient().getEmail();
//        String senderName =userEmmeteur.getName();
////    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
//        Integer IdRecepteur=compteEmmetteur.getIDClient();
//        User userRecepteur=userRepository.findUserByClientaccount(IdRecepteur);
//        String recipientName = userRecepteur.getName();
//        String subject = "Transfer successful";
//        String code = new SecureRandom().ints(6, 0, 36)
//                .mapToObj(i -> Integer.toString(i, 36))
//                .collect(Collectors.joining())
//                .toUpperCase();
//        String text = "Dear " + senderName + ",\n\nYour transfer of " + montant + " To " + recipientName + "has been successful. \n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";
//        emailService.sendEmail(to, subject, text);
    //é    transactionSrc.setValidationCode(code);
//
//
//        String A = userRecepteur.getName();// getClient().getEmail();
//        String sender = userEmmeteur.getName();
////    String recipientEmail = compteDestinataire.getNom(); here i can create Query to bring the name of the recipient
//        String recipient = userRecepteur.getName();
//        String body = "Transfer successful";
//
//        String words = "Dear " + recipient + ",\n\nYou have receive amount of " + montant + " From " + sender + ". \n\nThank you for using our banking services.\n\nBest regards,\nBank XYZ";
//        emailService.sendMail(A, body, words);
        //transactionSrc.setValidationCode(code);


    }

@Override
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }
@Override
    public Transaction findTransactionById(Integer IDtransaction) {
        return transactionRepository.findById(IDtransaction).get();
    }
@Override
    public void deleteTransactionById(Integer IDtransaction) {
        transactionRepository.deleteById(IDtransaction);
    }
}





/*
    @Transactional
//      (propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void depot(Integer IDClient, float Amount) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(IDClient).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() + Amount;
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setClientaccount(clientAccount);
        transaction.setAmount(Amount);
        transaction.setTransaction_type("Depot");
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void retrait(Integer IDClient, float Amount) throws Exception {
        ClientAccount clientAccount = clientAccountRepository.findById(IDClient).orElseThrow(() -> new Exception("Compte non trouvé"));
        float solde = clientAccount.getSolde() - Amount;
        if (solde < 0) {
            throw new Exception("Solde insuffisant");
        }
        clientAccount.setSolde(solde);
        clientAccountRepository.save(clientAccount);
        Transaction transaction = new Transaction();
        transaction.setClientaccount(clientAccount);
        transaction.setTransaction_type("Retrait");
        transaction.setAmount(Amount);
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);
    }

    @Override
    public int addTransaction(Transaction s) {
        Long source = s.getRibsource();
        Long des = s.getRibrecipient();
        List<ClientAccount> clientAccountList = iClientAccount.selectAll();
        for (ClientAccount clientAccount : clientAccountList) {
            if (clientAccount.getRib() == source) {
                float sold = clientAccount.getSolde();
                clientAccount.setSolde(sold + s.getAmount());
                if (clientAccount.getRib() == des) {
                    float a = clientAccount.getSolde();

                    clientAccount.setSolde(a + s.getAmount());
                }
            }
        }
        transactionRepository.save(s);
        return 0;
    }

    @Override
    public String approveTransaction(Transaction s) throws MessagingException {
        int code = 0;

        if ((addTransaction(s) == code)) {
            transactionRepository.save(s);
            return "transaction approuvée ";
        } else {
            return "Transaction non approuvée";
        }

    }
}

*/



