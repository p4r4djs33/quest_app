package com.example.quest_app_2.mapper.comment;

import com.example.quest_app_2.dto.CommentDTO;
import com.example.quest_app_2.entity.CommentEntity;
import com.example.quest_app_2.service.PostService;
import com.example.quest_app_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CommentMapperImpl implements CommentMapper{

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public CommentEntity toEntity(CommentDTO dto) {
        if (dto == null) {
            return null;
        } else {
            CommentEntity comment = new CommentEntity();
            comment.setText(dto.getText());
            comment.setUser(userService.getOneUserById(dto.getUserId()));
            comment.setPost(postService.getOnePostById(dto.getPostId()));
            return comment;
        }
    }

    @Override
    public CommentDTO toDTO(CommentEntity entity) {
        if (entity == null) {
            return null;
        } else {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(entity.getId());
            commentDTO.setUserId(entity.getUser().getId());
            commentDTO.setPostId(entity.getPost().getId());
            commentDTO.setText(entity.getText());
            return commentDTO;
        }
    }

    @Override
    public List<CommentEntity> toEntity(List<CommentDTO> dtoList) {
        if (dtoList == null) {
            return null;
        } else {
            List<CommentEntity> list = new ArrayList<>(dtoList.size());
            Iterator var3 = dtoList.iterator();
            while (var3.hasNext()) {
                CommentDTO commentDTO = (CommentDTO) var3.next();
                list.add(this.toEntity(commentDTO));
            }
            return list;
        }
    }

    @Override
    public List<CommentDTO> toDTO(List<CommentEntity> entityList) {
        if (entityList == null) {
            return null;
        } else {
            List<CommentDTO> list = new ArrayList<>(entityList.size());
            Iterator var3 = entityList.iterator();
            while (var3.hasNext()) {
                CommentEntity comment = (CommentEntity) var3.next();
                list.add(this.toDTO(comment));
            }
            return list;
        }
    }
}
