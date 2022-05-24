package com.example.quest_app_2.mapper.post;

import com.example.quest_app_2.dto.PostDTO;
import com.example.quest_app_2.entity.Post;
import com.example.quest_app_2.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface PostMapper extends EntityMapper<PostDTO, Post> {
}
