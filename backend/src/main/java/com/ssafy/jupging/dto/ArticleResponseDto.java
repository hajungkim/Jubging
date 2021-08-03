package com.ssafy.jupging.dto;

import com.ssafy.jupging.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponseDto {

    private Long articleId;
    private String content;
    private String photosPath;
    private int likeCnt;
    private Timestamp createdDate;
    private Long userId;

    private int commentCnt;

    private String nickname;

    public ArticleResponseDto(Article article){
        this.articleId=article.getArticleId();
        this.content=article.getContent();
        this.photosPath=article.getPhotosPath();
        this.likeCnt=article.getLikeCnt();
        this.createdDate= article.getCreatedDate();
        this.userId=article.getUserId();
    }

}
