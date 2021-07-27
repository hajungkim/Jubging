package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.repository.ArticleRepository;
import com.ssafy.jupging.dto.ArticleUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public Article save(Article article){
        return articleRepository.save(article);
    }

    @Transactional
    public Article findByArticleId(Long article_id) {
        Article article = articleRepository.findByArticleId(article_id)
                .orElseThrow(()->new IllegalArgumentException("게시글 정보가 없습니다"));

        return article;
    }

    @Transactional
    public void updateArticle(ArticleUpdateRequestDto requestDto) {

        Article article = articleRepository.findByArticleId(requestDto.getArticleId())
                .orElseThrow(()->new IllegalArgumentException("게시글 정보가 없습니다"));

        article.setContent(requestDto.getContent());
    }

    @Transactional
    public void deleteArticle(int article_id) {

        Article article = articleRepository.findByArticleId(article_id)
                .orElseThrow(()->new IllegalArgumentException("게시글 정보가 없습니다"));

        articleRepository.delete(article);
    }

    @Transactional
    public List<Article> findByUserId(int user_id) {

        return articleRepository.findByUserId(user_id);
    }

    @Transactional
    public List<Article> findTop10ByOrderByCreatedDateDesc() {
        return articleRepository.findTop10ByOrderByCreatedDateDesc();
    }
}

