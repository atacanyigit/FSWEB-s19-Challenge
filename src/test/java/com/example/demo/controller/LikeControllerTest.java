package com.example.demo.controller;


import com.example.demo.service.LikeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LikeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class LikeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean // Eğer Spring sürümün çok yeniyse ve hata alırsan @MockitoBean kullan
    private LikeService likeService;

    @Test
    void testLikeTweet() throws Exception {
        doNothing().when(likeService).likeTweet(anyLong(), anyLong());

        mockMvc.perform(post("/like")
                        .param("tweetId", "1")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDislikeTweet() throws Exception {
        doNothing().when(likeService).dislikeTweet(anyLong(), anyLong());

        mockMvc.perform(post("/like/dislike")
                        .param("tweetId", "1")
                        .param("userId", "1"))
                .andExpect(status().isOk());
    }
}