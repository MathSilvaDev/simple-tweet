package com.math.springsecurity.tweet.entity;

import com.math.springsecurity.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_tweets")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "tweet_id")
    private Long tweetId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, length = 200)
    private String content;

    @CreationTimestamp
    private Instant creationTimestamp;

    public Tweet(User user, String content){
        this.user = user;
        this.content = content;
    }
}
