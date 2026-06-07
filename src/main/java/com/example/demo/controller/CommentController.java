package com.example.demo.controller;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.Comment;
import com.example.demo.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public Comment save(@RequestBody CommentDto commentDto) {
        return commentService.save(commentDto);
    }

    @PutMapping("/{id}")
    public Comment update(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        return commentService.update(id, commentDto);
    }

    @DeleteMapping("/{id}")
    public Comment delete(@PathVariable Long id, @RequestParam Long currentUserId){
        return commentService.delete(id, currentUserId);
    }
}
