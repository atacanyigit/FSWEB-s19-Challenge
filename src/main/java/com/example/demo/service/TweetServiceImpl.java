package com.example.demo.service;

import com.example.demo.entity.Tweet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.TweetRepository;
import java.util.List;

@Service

public class TweetServiceImpl implements TweetService{

    private final TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet save(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet findById(Long id) {
        return tweetRepository.findById(id).orElseThrow(() -> new RuntimeException("No tweets found! : " + id));
    }


    @Override
    public Tweet update(Long id, Tweet tweet) {
        Tweet tweets= tweetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No tweets found!: " + id));

        tweets.setName(tweet.getName());

        return tweetRepository.save(tweets);
    }

    @Override
    public Tweet delete(Long id) {
        Tweet tweets = tweetRepository.findById(id).orElseThrow(() -> new RuntimeException("Silinecek tweet bulunamadı!" +id));

        tweetRepository.delete(tweets);

        return tweets;

    }

    @Override
    public List <Tweet> findByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }
}
