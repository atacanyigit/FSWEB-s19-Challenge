package com.example.demo.service;

import com.example.demo.dto.RetweetDto;
import com.example.demo.entity.Retweet;

public interface RetweetService {

    Retweet retweet(RetweetDto retweetDto);


    void undoRetweet(Long tweetId, Long userId);
}
