package com.example.demo;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TweetRepository;
import com.example.demo.service.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentServiceImpl;

    @Mock // EKSİKTİ: TweetRepository eklendi
    private TweetRepository tweetRepository;

    @Test
    void shouldSaveCommentSuccessfully() {
        CommentDto commentDto = new CommentDto();

        Comment commentEntity = new Comment();
        when(commentRepository.save(any(Comment.class))).thenReturn(commentEntity);

        Comment savedComment = commentServiceImpl.save(commentDto);

        assertNotNull(savedComment);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void shouldThrowExceptionWhenTweetNotFound() {

        when(tweetRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            commentServiceImpl.save(new CommentDto());
        });
    }
}
