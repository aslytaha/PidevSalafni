package com.example.pidev.Repositories;

import com.example.pidev.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

//    @Query("select u.Password from User u where u.Email=?1")
//    public String getPasswordByUserEmail(String Email);

    @Query(" select u from User u " +
            " where u.Username = ?1")
    User findByUsername(String username);

    User  findByVerificationToken(String Token);

<<<<<<< Updated upstream
    User  findByVerificationToken(String Token);

=======
>>>>>>> Stashed changes
//    boolean existsByEmail(String Email);
//
//    User findUserByEmail(String Email);
//
    @Query(" select u from User u " +
        " where u.Phone = ?1")
    User findByPhone(Long Phone);
<<<<<<< Updated upstream

    User findByVerificationCode(String code);
////////////Nader///////////////
    @Query("SELECT u FROM User u WHERE u.clientaccount.IDClient = ?1")
    User findUserByClientaccount(@Param("clientId") Long clientId);

=======

    User findByVerificationCode(String code);

    @Query("SELECT u FROM User u WHERE u.clientaccount.IDClient = :clientId")
    User findUserByClientaccount(@Param("clientId") Integer clientId);
>>>>>>> Stashed changes
}
