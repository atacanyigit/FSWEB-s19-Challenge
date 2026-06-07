package com.example.demo.controller;

import com.example.demo.dto.RetweetDto;
import com.example.demo.entity.Retweet;
import com.example.demo.service.RetweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    @Autowired
    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public Retweet retweet(@RequestBody RetweetDto retweetDto) {
        return retweetService.retweet(retweetDto);
    }

    // Eğer id kullanmıyorsan @DeleteMapping("/{id}") yerine sadece @DeleteMapping kullan
    @DeleteMapping
    public void undoRetweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        retweetService.undoRetweet(tweetId, userId);
    }
}