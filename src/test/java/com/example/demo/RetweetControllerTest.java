package com.example.demo;

import com.example.demo.controller.RetweetController;
import com.example.demo.dto.RetweetDto;
import com.example.demo.entity.Retweet;
import com.example.demo.service.RetweetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RetweetController.class)
public class RetweetControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockitoBean
    private RetweetService retweetService;

    @Test
    public void testRetweet() throws Exception {
        // Servis katmanının davranışını simüle ediyoruz
        when(retweetService.retweet(any(RetweetDto.class))).thenReturn(new Retweet());


        mockMvc.perform(post("/retweet")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"tweetId\": 1, \"userId\": 1}"))
                .andExpect(status().isOk());
    }
}