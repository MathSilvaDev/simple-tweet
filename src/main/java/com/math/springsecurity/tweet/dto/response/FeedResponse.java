package com.math.springsecurity.tweet.dto.response;

import com.math.springsecurity.tweet.dto.FeedItemDto;

import java.util.List;

public record FeedResponse(
        List<FeedItemDto> feedItens,
        int page,
        int pageSize,
        int totalPage,
        long totalElements
) {}
