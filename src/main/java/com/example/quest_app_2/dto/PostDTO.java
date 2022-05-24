package com.example.quest_app_2.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Long id;
    private String text;
    private String title;
    private Long userId;
}
