package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.LikeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeLogRepository extends JpaRepository<LikeLog, Long> {
    LikeLog save(LikeLog likeLog);

    void deleteByUserIdAndArticleId(long userId, long articleId);

    List<LikeLog> findAllByUserIdOrderByCreatedDateDesc(long userId);

    List<LikeLog> findAllByArticleId(long article_id);
}
