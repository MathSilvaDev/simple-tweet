package com.math.springsecurity.user.dto.response;

import java.util.Set;
import java.util.UUID;

public record UserResponse (
  UUID id,
  String username,
  Set<String> roles
){}
