package com.example.pidev.Service.Classes;

import com.example.pidev.Entities.User;
import com.example.pidev.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


}
