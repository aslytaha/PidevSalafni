package com.example.pidev.Repositories;

import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u.Password from User u where u.Email=?1")
    public String getPasswordByUserEmail(String Email);

    @Query(" select u from User u " +
            " where u.Username = ?1")
    User findByUsername(String username);

//    User findByEmail(String Email);
}
