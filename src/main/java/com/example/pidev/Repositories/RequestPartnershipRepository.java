package com.example.pidev.Repositories;

import com.example.pidev.Entities.RequestPartnership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestPartnershipRepository extends JpaRepository<RequestPartnership,Long> {

   // @Query("select u.clientaccount.IDClient , c.IDClient from User u, ClientAccount c where u.clientaccount.IDClient=c.IDClient")
   // void findUser (Integer IDClient );

   List <RequestPartnership> findAllByOrderByAmountPayedDesc();

}
