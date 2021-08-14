package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByArticleId(Long article_id);

    List<Article> findByUserIdOrderByCreatedDate(Long user_id);

    List<Article> findByOrderByCreatedDateDesc();

    int countByUserId(Long userId);
}
