package com.example.quest_app_2.service;

import com.example.quest_app_2.dto.CommentDTO;
import com.example.quest_app_2.entity.CommentEntity;
import com.example.quest_app_2.entity.Post;
import com.example.quest_app_2.entity.User;
import com.example.quest_app_2.mapper.comment.CommentMapper;
import com.example.quest_app_2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentEntity> getAllCommentByUserIdAndPostId(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return (commentRepository.findByUserIdAndPostId(userId.get(), postId.get())) ;
        } else if (userId.isPresent()) {
            return (commentRepository.findByUserId(userId.get()));
        } else if (userId.isPresent()) {
            return (commentRepository.findByPostId(userId.get()));
        } else {
            return (commentRepository.findAll());
        }
    }

    public CommentEntity getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public CommentEntity createOneComment(CommentDTO commentDTO) {
        User user = userService.getOneUserById(commentDTO.getUserId());
        Post post = postService.getOnePostById(commentDTO.getPostId());
        if (user != null && post != null) {
            CommentEntity comment = new CommentEntity();
            comment.setPost(post);
            comment.setUser(user);
            comment.setText(commentDTO.getText());
            commentRepository.save(comment);
            return comment;
        }
        return null;

    }

    public CommentEntity updateOneComment(Long commentId, CommentDTO commentDTO) {
        CommentEntity comment = commentRepository.findById(commentId).get();
        if (comment != null) {
            comment.setText(commentDTO.getText());
            return commentRepository.save(comment);
        }
        return null;
    }

    public void deleteOneComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
