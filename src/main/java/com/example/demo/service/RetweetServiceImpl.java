package com.example.demo.service;

import com.example.demo.dto.RetweetDto;
import com.example.demo.entity.Retweet;
import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.repository.RetweetRepository;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetweetServiceImpl implements RetweetService{

    private final RetweetRepository retweetRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.retweetRepository = retweetRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Retweet retweet(RetweetDto retweetDto) {
        Tweet tweet = tweetRepository.findById(retweetDto.getTweetId())
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
        User user = userRepository.findById(retweetDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        Retweet retweet = new Retweet();
        retweet.setOriginalTweet(tweet);
        retweet.setUser(user);
        return retweetRepository.save(retweet);
    }

    @Override
    public void undoRetweet(Long tweetId, Long userId) {
        retweetRepository.deleteByOriginalTweetIdAndUserId(tweetId, userId);

    }
}



