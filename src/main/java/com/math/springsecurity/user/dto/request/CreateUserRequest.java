package com.math.springsecurity.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String username,

        @NotBlank 
        String password
){}
