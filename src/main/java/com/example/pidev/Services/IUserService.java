package com.example.pidev.Services;

import com.example.pidev.Entities.User;

import java.util.List;

public interface IUserService {

    List<User> retrieveAllUsers();

    User addUser(User user);

    User updateUser (User user);

    User retrieveUser (Long IdUser);

    void deleteUser( Long IdUser);

    User retrieveUserByUsername(String Username);

    public User registerUser(User user);




}
