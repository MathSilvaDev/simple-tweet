package com.math.springsecurity.tweet.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTweetRequest(
        @NotBlank
        @Size(max = 200)
        String content
){}
