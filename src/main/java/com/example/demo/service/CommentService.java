package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;

public interface CommentService {

    Comment save(CommentDto commentDto);

    Comment update(Long id, CommentDto commentDto);


    Comment delete(Long id, Long currentUserId);
}
