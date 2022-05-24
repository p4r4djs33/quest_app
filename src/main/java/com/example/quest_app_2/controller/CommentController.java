package com.example.quest_app_2.controller;

import com.example.quest_app_2.dto.CommentDTO;
import com.example.quest_app_2.mapper.comment.CommentMapper;
import com.example.quest_app_2.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping("")
    public List<CommentDTO> getAllComments(@RequestParam Optional<Long> userId,
                                           @RequestParam Optional<Long> postId) {
        return commentMapper.toDTO(commentService.getAllCommentByUserIdAndPostId(userId, postId));
    }

    @PostMapping
    public CommentDTO createOneComment(@RequestBody CommentDTO commentDTO) {
        return commentMapper.toDTO(commentService.createOneComment(commentDTO));
    }

    @GetMapping("/{commentId}")
    public CommentDTO getOneComment(@PathVariable Long commentId) {
        return commentMapper.toDTO(commentService.getOneCommentById(commentId));
    }

    @PutMapping("/{commentId}")
    public CommentDTO updateOneComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        return commentMapper.toDTO(commentService.updateOneComment(commentId, commentDTO));
    }
    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId) {
        commentService.deleteOneComment(commentId);
    }
}
