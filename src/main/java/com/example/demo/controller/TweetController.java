package com.example.demo.controller;

import com.example.demo.entity.Tweet;
import com.example.demo.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService){
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<Tweet> findAll(){
        return tweetService.findAll();
    }

    @GetMapping("/findById")
    public Tweet findById(@PathVariable Long id){
        return tweetService.findById(id);
    }

    @GetMapping("/findByUserId")
    public List<Tweet> findByUserId(@RequestParam Long userId) {
        return tweetService.findByUserId(userId);
    }

    @PostMapping
    public Tweet save(@RequestBody Tweet tweet){
        return tweetService.save(tweet);
    }

    @PutMapping("/{id}")
    public Tweet update(@PathVariable Long id, @RequestBody Tweet tweet){
        return tweetService.update(id, tweet);

    }

    @DeleteMapping("/{id}")
    public Tweet delete(@PathVariable Long id){
        return tweetService.delete(id);
    }
}
