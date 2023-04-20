package com.example.pidev.Controller;


import com.example.pidev.Entities.JwtRequest;
import com.example.pidev.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @Autowired
    JwtService jwtService;

    @PostMapping({"/authenticate"})
    public String createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}

