package com.math.springsecurity.auth.dto.response;

public record LoginResponse(String accessToken, Long expiresIn) {
}
