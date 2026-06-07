package com.example.demo.repository;

import com.example.demo.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetweetRepository extends JpaRepository<Retweet, Long> {

    void deleteByOriginalTweetIdAndUserId(Long tweetId, Long userId);
}
