package com.example.quest_app_2.controller;

import com.example.quest_app_2.dto.PostDTO;
import com.example.quest_app_2.mapper.post.PostMapper;
import com.example.quest_app_2.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private PostMapper postMapper;

    @GetMapping("")
    public List<PostDTO> getAllPosts(@RequestParam Optional<Long> userId) {
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public PostDTO createOnePost(@RequestBody PostDTO postDTO) {
        return postService.createOnePost(postDTO);
    }

    @GetMapping("/{postId}")
    public PostDTO getOnePost(@PathVariable Long postId) {
        return postMapper.toDTO(postService.getOnePostById(postId));
    }

    @PutMapping("/{postId}")
    public PostDTO updateOnePost(@PathVariable Long postId, @RequestBody PostDTO postDTO) {
        return postMapper.toDTO(postService.updateOnePostById(postId, postDTO)) ;
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId) {
        postService.deleteOnePostById(postId);
    }
}
