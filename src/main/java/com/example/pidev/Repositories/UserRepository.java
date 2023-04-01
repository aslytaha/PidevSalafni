package com.example.pidev.Repositories;

import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
    @Query("select u.clientaccount.IDClient , c.IDClient from User u, ClientAccount c where u.clientaccount.IDClient=c.IDClient")
   User findUser (Integer IDClient );


    @Query("SELECT u FROM User u WHERE u.clientaccount.IDClient = :clientId")
    User findUserByClientaccount(@Param("clientId") Integer clientId);
}
