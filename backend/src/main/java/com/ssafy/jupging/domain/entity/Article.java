package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.ArticleSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;

    @Column(length = 500, nullable = false)
    private String content;

    @Column(length = 500, nullable = false)
    private String photosPath;

    @Column(nullable = false)
    private int likeCnt;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdDate;

    @Column(nullable = false)
    private int userId;

    public static Article saveArticle(ArticleSaveRequestDto request) {
        Article article = new Article();
        article.content=request.getContent();
        article.userId=request.getUser_id();
        return article;
    }
}