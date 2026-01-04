package com.math.springsecurity.tweet.dto.response;

public record FeedItem(
        long tweetId,
        String content,
        String username
) {}
