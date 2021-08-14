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
    public Article findByArticleId(Long articleId) {
        Article article = articleRepository.findByArticleId(articleId)
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
    public void deleteArticle(Long articleId) {

        Article article = articleRepository.findByArticleId(articleId)
                .orElseThrow(()->new IllegalArgumentException("게시글 정보가 없습니다"));

        articleRepository.delete(article);
    }

    @Transactional
    public List<Article> findByUserId(Long userId) {
        return articleRepository.findByUserIdOrderByCreatedDate(userId);
    }

    @Transactional
    public List<Article> findByOrderByCreatedDateDesc() {
        return articleRepository.findByOrderByCreatedDateDesc();
    }

    public int countByUserId(Long userId) {
        return articleRepository.countByUserId(userId);
    }
}

