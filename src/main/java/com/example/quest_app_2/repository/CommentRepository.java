package com.example.quest_app_2.repository;

import com.example.quest_app_2.dto.CommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.quest_app_2.entity.CommentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByUserIdAndPostId(Long aLong, Long aLong1);

    List<CommentEntity> findByUserId(Long userId);

    List<CommentEntity> findByPostId(Long postId );
}
