package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.dto.CommentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByCommentId(long comment_id);

    Long countByArticleId(long article_id);

    //List<CommentMapping> findAllByArticleId(long article_id);
    List<Comment> findAllByArticleId(long article_id);
}
