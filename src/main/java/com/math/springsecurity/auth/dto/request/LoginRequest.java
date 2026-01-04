package com.math.springsecurity.auth.dto.request;

public record LoginRequest(
        String username,
        String password
){}
