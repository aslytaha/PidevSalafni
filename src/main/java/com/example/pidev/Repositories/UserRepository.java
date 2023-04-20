package com.example.pidev.Repositories;

import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.clientaccount.IDClient = :clientId")
    User findUserByClientaccount(@Param("clientId") Integer clientId);

}
