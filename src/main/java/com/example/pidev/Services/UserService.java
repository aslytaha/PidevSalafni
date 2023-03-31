package com.example.pidev.Services;

import com.example.pidev.Entities.ClientAccount;
import com.example.pidev.Entities.Role;
import com.example.pidev.Entities.User;
import com.example.pidev.Services.IUserService;
import com.example.pidev.Repositories.ClientAccountRepository;
import com.example.pidev.Repositories.RoleRepository;
import com.example.pidev.Repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Service
@AllArgsConstructor
@NoArgsConstructor

    public class UserService implements IUserService {



//         @Autowired
//         MailService mailService;
        @Autowired
        ClientAccountRepository clientAccountRepository;
        @Autowired
        RoleRepository roleRepository;
        @Autowired
        private UserRepository userRepository;

       @Autowired
       AuthenticationManager authenticationManager ;
       @Autowired
       PasswordEncoder passwordEncoder;





    @Override
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User retrieveUser(Long IdUser) {
        return userRepository.findById(IdUser).get();
    }

    public void deleteUser(Long id) {
            userRepository.deleteById(id);
        }

    @Override
    public User retrieveUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User registerUser(User user) {
        ClientAccount clientAccount=new ClientAccount();
        clientAccountRepository.save(clientAccount);
        Role role = roleRepository.findRoleByRoleName("User");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setPassword(getEncodedPassword(user.getPassword()));
        user.setClientaccount(clientAccount);
//        try {
//            mail.sendVerificationEmail(user);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
        return userRepository.save(user) ;
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }


}


