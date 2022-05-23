package com.example.quest_app_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.quest_app_2.entity.CommentEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
