package com.example.pidev.Interfaces;

import com.example.pidev.Entities.Reclamation;
import com.example.pidev.Enumerations.StatusTransaction;
import com.example.pidev.Entities.User;

import java.util.List;

public interface IReclamationService {


    Reclamation add(Reclamation rec);

    Reclamation edit(Reclamation rec);

    List<Reclamation> selectAll();

    Reclamation selectById(int reclamationId);

    void deleteById(int roleId);

    void delete(Reclamation rec);

    List<Reclamation> addAll(List<Reclamation> listrec);

    void deleteAll(List<Reclamation> list);
    void deleteAll();
    List<Reclamation> findByStatus(StatusTransaction status);

    String getCurrentUserName();

    User getUser(String username);
}