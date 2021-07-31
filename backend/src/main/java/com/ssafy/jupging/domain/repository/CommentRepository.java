package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentId(long comment_id);

    int countByArticleId(long article_id);

    List<Comment> findAllByArticleId(long article_id);
}
