package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TweetRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, TweetRepository tweetRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Comment save(CommentDto commentDto) {
        Tweet tweet = tweetRepository.findById(commentDto.getTweetId())
                .orElseThrow(() -> new RuntimeException("Tweet bulunamadı"));
        User user = userRepository.findById(commentDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));


        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setTweet(tweet);
        comment.setUser(user);


        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Long id, CommentDto commentDto) {
        Comment comments = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Yorum bulunamadı"));


        comments.setText(commentDto.getText());


        return commentRepository.save(comments);
    }

    @Override
    public Comment delete(Long id, Long currentUserId) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No comments found!"));


        if (!comment.getUser().getId().equals(currentUserId)) {
            throw new RuntimeException("Bu yorumu silme yetkiniz yok.");
        }


        commentRepository.delete(comment);
        return comment;
    }
}
