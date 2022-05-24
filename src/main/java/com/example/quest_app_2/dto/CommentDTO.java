package com.example.quest_app_2.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long postId;
    private Long userId;
    private String text;
}
