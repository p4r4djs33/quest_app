package com.example.quest_app_2.service;

import com.example.quest_app_2.dto.PostDTO;
import com.example.quest_app_2.entity.Post;
import com.example.quest_app_2.entity.User;
import com.example.quest_app_2.mapper.post.PostMapper;
import com.example.quest_app_2.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private PostMapper postMapper;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<PostDTO> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postMapper.toDTO(postRepository.findByUserId(userId.get()));
        }
        return postMapper.toDTO(postRepository.findAll());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public PostDTO createOnePost(PostDTO postDTO) {
        User user = userService.getOneUserById(postDTO.getUserId());
        if (user == null) {
            return null;
        }
        Post postSave = postMapper.toEntity(postDTO);
        postRepository.save(postSave);
        return postMapper.toDTO(postSave);
    }

    public Post updateOnePostById(Long postId, PostDTO postDTO) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            Post postUpdate = post.get();
            postUpdate.setText(postDTO.getText());
            postUpdate.setTitle(postDTO.getTitle());
            postRepository.save(postUpdate);
            return postUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId) {
        postRepository.deleteById(postId);
    }
}
