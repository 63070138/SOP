package com.example.auth_service.controllers;


import com.example.auth_service.entities.AuthRequest;
import com.example.auth_service.entities.AuthResponse;
import com.example.auth_service.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(final AuthService authService){
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> logIn(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authService.logIn(authRequest));
    }
}