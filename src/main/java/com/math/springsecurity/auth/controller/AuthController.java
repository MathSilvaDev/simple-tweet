package com.math.springsecurity.auth.controller;

import com.math.springsecurity.auth.dto.request.LoginRequest;
import com.math.springsecurity.auth.dto.response.LoginResponse;
import com.math.springsecurity.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return ResponseEntity
                .ok(authService.login(loginRequest));
    }
}
