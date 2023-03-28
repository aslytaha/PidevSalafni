package com.example.pidev.Controllers;

import com.example.pidev.Entities.User;
import com.example.pidev.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class UserController {


    @Autowired
    private UserService userService;





    @Autowired
    private UserDetailsService userDetailsService;



      @PostMapping({"/register"})
        public User registerNewUser(@RequestBody User user) {
        return userService.registerUser(user);
    }




        @GetMapping("/retrieve-all-Users")
        public List<User> getUsers() {
            List<User> listUsers = userService.retrieveAllUsers();
            return listUsers;

        }


        @GetMapping("/retrieve-user/{iduser}")
        public User retrieveUserById(@PathVariable("iduser") Long IdUser) {
            return userService.retrieveUser(IdUser);
        }

      @GetMapping("/retrieve/{username}")
       public User getUserByUsername(@PathVariable("username") String username) {
         return userService.retrieveUserByUsername(username);
    }



    @PostMapping("/add-User")
        public User addUser(@RequestBody User u) {
            User  user = userService.addUser(u);
            return user;
        }

        @DeleteMapping("/remove-user/{iduser}")
        public void removeUser(@PathVariable("iduser") Long IdUser) {
            userService.deleteUser(IdUser);
        }


        @PutMapping("/update-User")
        public User updateUser(@RequestBody User  u) {
            User user = userService.updateUser(u);
            return user;
        }
}




//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody Authentication authenticationRequest)
//            throws AuthenticationException {
//
//        // Authenticate the user
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//
//        // Generate a JWT for the user
//        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//
//        // Send the JWT as a response to the client
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }

//    @PostMapping("/login")
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
//        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//
//        final String token = jwtTokenUtil.generateToken(userDetails);
//
//        return ResponseEntity.ok(new JwtResponse(token));
//    }

//    private void authenticate(String username, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER_DISABLED", e);
//        } catch (BadCredentialsException e) {
//            throw new Exception("INVALID_CREDENTIALS", e);
//        }
//    }







//    @RequestMapping(value = "/registration", method = RequestMethod.GET)
//    public String registration(Model model) {
//        model.addAttribute("userForm", new User());
//
//        return "registration";
//    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
//        userValidator.validate(userForm, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "registration";
//        }
//
//        userService.save(userForm);
//
//        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
//
//        return "redirect:/welcome";
//    }


//    @GetMapping("/register")
//    public String showRegistrationForm(Model model) {
//        model.addAttribute("user", new User());
//
//        return "signup_form";
//    }
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String login(Model model, String error, String logout) {
//        if (error != null)
//            model.addAttribute("error", "Your username and password is invalid.");
//
//        if (logout != null)
//            model.addAttribute("message", "You have been logged out successfully.");
//
//        return "login Succeed";
//    }