package com.example.demo.service;

import com.example.demo.entity.Like;
import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService{

    private final LikeRepository likeRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void likeTweet(Long tweetId, Long userId) {
        if (likeRepository.findByTweetIdAndUserId(tweetId, userId).isPresent()) {
            return;
        }
            Tweet tweet = tweetRepository.findById(tweetId)
                    .orElseThrow(() -> new RuntimeException("No tweets found!"));
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new RuntimeException("No users found!"));


            Like like = new Like();
            like.setTweet(tweet);
            like.setUser(user);
            likeRepository.save(like);

    }

    @Transactional
    @Override
    public void dislikeTweet(Long tweetId, Long userId) {
        likeRepository.deleteByTweetIdAndUserId(tweetId, userId);

    }
}





