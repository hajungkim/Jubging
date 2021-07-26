package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentId(int comment_id);

    Long countByArticleId(int article_id);
}
