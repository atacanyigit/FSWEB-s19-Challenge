package com.example.demo;

import com.example.demo.controller.CommentController;
import com.example.demo.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CommentService commentService;

    @Test
    void testCreateCommentSuccess() throws Exception{
        mockMvc.perform(post("/comment")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"tweetId\": 1, \"userId\": 1, \"text\": \"Harika bir içerik!\"}"))
                .andExpect(status().isOk());
    }
    @Test
    void testCreateCommentInvalidData() throws Exception {
        mockMvc.perform(post("/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testDeleteCommentSuccess() throws Exception {
        mockMvc.perform(delete("/comment/{commentId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCommentSuccess() throws Exception {
        mockMvc.perform(put("/comment/{commentId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\": \"Güncellenmiş yorum\"}"))
                .andExpect(status().isOk());
    }


}
