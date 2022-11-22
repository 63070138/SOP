package com.example.bookstoreservice.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/recommended")
public class bookController {
    @GetMapping
    public String getBook(){
        return "Spring in Action (Manning), Cloud Native Java (0'Reilly), Learning Spring Boot (Packt)" ;
    }
}
