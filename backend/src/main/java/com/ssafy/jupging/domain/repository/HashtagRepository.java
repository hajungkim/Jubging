package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    void deleteAllByArticleId(Long article_id);

    List<Hashtag> findHashtagDistinctByContentContains(String hashtag);

    List<Hashtag> findAllByContent(String hashtag);

    List<Hashtag> findAllByArticleId(Long articleId);
}
