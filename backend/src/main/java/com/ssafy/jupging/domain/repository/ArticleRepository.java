package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    Optional<Article> findByArticleId(int article_id);

    List<Article> findByUserId(int user_id);

    List<Article> findTop10ByOrderByCreatedDateDesc();
}
