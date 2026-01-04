package com.math.springsecurity.user.dto.request;

public record CreateUserRequest(
        String username,
        String password
){}
