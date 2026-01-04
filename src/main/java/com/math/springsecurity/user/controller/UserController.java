package com.math.springsecurity.user.controller;

import com.math.springsecurity.user.dto.request.CreateUserRequest;
import com.math.springsecurity.user.entity.User;
import com.math.springsecurity.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest dto){
        return ResponseEntity
                .ok(userService.createUser(dto));
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<User>> listAllUsers(){
        return ResponseEntity
                .ok(userService.findAllUsers());
    }
}
