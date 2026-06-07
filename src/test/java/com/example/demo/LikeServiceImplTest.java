package com.example.demo;

import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.dto.LikeDto;
import com.example.demo.entity.Like;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.LikeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LikeServiceImplTest {

    @Mock
    private LikeRepository likeRepository;

    @Mock
    private TweetRepository tweetRepository;

    @InjectMocks
    private LikeServiceImpl likeServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldLikeTweetSuccessfully() {

        Long tweetId = 1L;
        Long userId = 1L;
        LikeDto likeDto = new LikeDto();
        likeDto.setTweetId(1L);


        when(tweetRepository.findById(1L)).thenReturn(Optional.of(new Tweet()));
        when(likeRepository.save(any(Like.class))).thenReturn(new Like());
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User()));

        likeServiceImpl.likeTweet(1L, 1L);

        verify(likeRepository, times(1)).save(any(Like.class));
    }

    @Test
    void shouldThrowExceptionWhenTweetNotFoundForLike() {
        when(tweetRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            likeServiceImpl.likeTweet(99L, 1L);
        });
    }
}