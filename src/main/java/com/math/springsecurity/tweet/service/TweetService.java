package com.math.springsecurity.tweet.service;

import com.math.springsecurity.role.entity.Role;
import com.math.springsecurity.tweet.dto.response.FeedItem;
import com.math.springsecurity.tweet.dto.request.CreateTweetRequest;
import com.math.springsecurity.tweet.dto.response.FeedResponse;
import com.math.springsecurity.tweet.entity.Tweet;
import com.math.springsecurity.tweet.repository.TweetRepository;
import com.math.springsecurity.user.entity.User;
import com.math.springsecurity.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository,
                        UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public FeedResponse feed(int page, int pageSize){

        Page<FeedItem> tweets = tweetRepository.findAll(
                    PageRequest.of(page, pageSize, Sort.Direction.DESC, "creationTimestamp")
                ).map(tweet ->
                        new FeedItem(
                                tweet.getTweetId(),
                                tweet.getContent(),
                                tweet.getUser().getUsername()
                        )
                );

        return new FeedResponse(
                tweets.getContent(),
                page,
                pageSize,
                tweets.getTotalPages(),
                tweets.getTotalElements()
        );
    }

    public void createTweet(CreateTweetRequest request, UUID userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Tweet tweet = new Tweet(user, request.content());
        tweetRepository.save(tweet);
    }

    public void deleteTweet(Long tweetId, UUID userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));

        Tweet tweet = tweetRepository.findById(tweetId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        boolean isAdmin = user.getRoles()
                .stream()
                .anyMatch(role ->
                        role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

        if (!isAdmin && !tweet.getUser().getUserId().equals(userId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        tweetRepository.deleteById(tweetId);
    }
}
