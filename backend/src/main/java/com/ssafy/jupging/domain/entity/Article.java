package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.ArticleSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String photosPath;

    @Column(nullable = false)
    private int likeCnt;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private Long userId;

    public static Article saveArticle(ArticleSaveRequestDto request) {
        Article article = new Article();
        article.content = request.getContent();
        article.userId = request.getUserId();
        article.photosPath = request.getPhotosPath();
        article.createdDate = LocalDateTime.now();
        return article;
    }
}