package com.example.quest_app_2.mapper.comment;

import com.example.quest_app_2.dto.CommentDTO;
import com.example.quest_app_2.entity.CommentEntity;
import com.example.quest_app_2.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper extends EntityMapper<CommentDTO, CommentEntity> {
}
