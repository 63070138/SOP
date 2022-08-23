package com.example.lab3;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GeneratePasswordService {
    @RequestMapping(value = "/{fname}.generate", method = RequestMethod.GET)
    public String generate(@PathVariable("fname") String fname){
        double password;
        password = Math.floor(Math.random() * 10000000);
        int rpassword = (int) password;
        return "Hi, "+fname+"<br>" +
                "Your new password is "+ rpassword;
    }
}

