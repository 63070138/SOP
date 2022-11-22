package com.example.lab12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Lab12Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab12Application.class, args);
    }

}
