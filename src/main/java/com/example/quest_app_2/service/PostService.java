package com.example.quest_app_2.service;

import com.example.quest_app_2.entity.Post;
import com.example.quest_app_2.entity.User;
import com.example.quest_app_2.repository.PostRepository;
import com.example.quest_app_2.repository.UserRepository;
import com.example.quest_app_2.request.PostCreateRequest;
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

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public List<Post> getAllPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId.get());
        }
        return postRepository.findAll();
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest postCreateRequest) {
        User user = userService.getOneUserById(postCreateRequest.getUserId());
        if (user == null) {
            return null;
        }
        Post postSave = new Post();
        postSave.setId(postCreateRequest.getId());
        postSave.setText(postCreateRequest.getText());
        postSave.setTitle(postCreateRequest.getTitle());
        postSave.setUser(user);
        return postRepository.save(postSave);
    }
}
