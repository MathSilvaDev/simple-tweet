package com.math.springsecurity.tweet.dto.response;

import java.util.List;

public record FeedResponse(
        List<FeedItem> feedItens,
        int page,
        int pageSize,
        int totalPage,
        long totalElements
) {}
