package com.example.demo.controller;

import com.example.demo.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public void like(@RequestParam Long tweetId, @RequestParam Long userId){
        likeService.likeTweet(tweetId, userId);
    }

    @PostMapping("/dislike")
    public void dislike(@RequestParam Long tweetId, @RequestParam Long userId){
        likeService.dislikeTweet(tweetId, userId);
    }

}
