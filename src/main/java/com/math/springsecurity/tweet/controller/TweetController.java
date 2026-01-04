package com.math.springsecurity.tweet.controller;

import com.math.springsecurity.tweet.dto.request.CreateTweetRequest;
import com.math.springsecurity.tweet.dto.response.FeedResponse;
import com.math.springsecurity.tweet.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping("/feeds")
    public ResponseEntity<FeedResponse> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return ResponseEntity.ok(tweetService.feed(page, pageSize));
    }

    @PostMapping("/tweets")
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetRequest dto,
                                            JwtAuthenticationToken token){

        tweetService.createTweet(dto, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tweets/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId,
                                            JwtAuthenticationToken token){

        tweetService.deleteTweet(tweetId, token);
        return ResponseEntity.ok().build();
    }
}
