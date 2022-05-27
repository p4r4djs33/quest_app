
package com.example.quest_app_2.mapper.post;

import com.example.quest_app_2.dto.PostDTO;
import com.example.quest_app_2.entity.Post;
import com.example.quest_app_2.entity.User;
import com.example.quest_app_2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private UserService userService;

    public PostMapperImpl() {
    }
    @Override
    public List<Post> toEntity(List<PostDTO> dtoList) {
        if (dtoList == null) {
            return null;
        } else {
            List<Post> list = new ArrayList(dtoList.size());
            Iterator var3 = dtoList.iterator();

            while(var3.hasNext()) {
                PostDTO postDTO = (PostDTO) var3.next();
                list.add(this.toEntity(postDTO));
            }

            return list;
        }
    }
    @Override
    public List<PostDTO> toDTO(List<Post> entityList) {
        if (entityList == null) {
            return null;
        } else {
            List<PostDTO> list = new ArrayList(entityList.size());
            Iterator var3 = entityList.iterator();

            while(var3.hasNext()) {
                Post post = (Post) var3.next();
                list.add(this.toDTO(post));
            }

            return list;
        }
    }

    @Override
    public Post toEntity(PostDTO postDTO) {
        if (postDTO == null) {
            return null;
        } else {
            Post post = new Post();
            if (postDTO.getId() != null) {
                post.setId(postDTO.getId());
            }
            User user = userService.getOneUserById(postDTO.getUserId());
            post.setUser(user);
            post.setTitle(postDTO.getTitle());
            post.setText(postDTO.getText());
            return post;
        }
    }

    @Override
    public PostDTO toDTO(Post post) {
        if (post == null) {
            return null;
        } else {
            PostDTO postDTO = new PostDTO();
            if (post.getId() != null) {
                postDTO.setId(post.getId());
            }
            postDTO.setUserId(post.getUser().getId());
            postDTO.setTitle(post.getTitle());
            postDTO.setText(post.getText());
            postDTO.setUserName(post.getUser().getUserName());
            return postDTO;
        }
    }
}


