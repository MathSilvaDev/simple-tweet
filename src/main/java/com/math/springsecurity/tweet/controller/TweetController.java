package com.math.springsecurity.tweet.controller;

import com.math.springsecurity.tweet.dto.request.CreateTweetRequest;
import com.math.springsecurity.tweet.dto.response.FeedResponse;
import com.math.springsecurity.tweet.service.TweetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<FeedResponse> feed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize){

        return ResponseEntity.ok(tweetService.feed(page, pageSize));
    }

    @PostMapping
    public ResponseEntity<Void> createTweet(@Valid @RequestBody CreateTweetRequest request,
                                            JwtAuthenticationToken token){

        UUID userId = UUID.fromString(token.getName());
        tweetService.createTweet(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") Long tweetId,
                                            JwtAuthenticationToken token){

        UUID userId = UUID.fromString(token.getName());
        tweetService.deleteTweet(tweetId, userId);

        return ResponseEntity.noContent().build();
    }
}
