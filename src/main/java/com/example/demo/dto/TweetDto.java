package com.example.demo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TweetDto {

    private String content;
    private Long userId;
}
