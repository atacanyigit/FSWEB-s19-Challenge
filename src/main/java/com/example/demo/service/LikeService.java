package com.example.demo.service;

public interface LikeService {
    void likeTweet(Long tweetId, Long userId);


    void dislikeTweet(Long tweetId, Long userId);
}
