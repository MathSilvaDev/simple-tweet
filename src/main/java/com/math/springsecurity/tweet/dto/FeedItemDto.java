package com.math.springsecurity.tweet.dto;

public record FeedItemDto(
        long tweetId,
        String content,
        String username
) {}
