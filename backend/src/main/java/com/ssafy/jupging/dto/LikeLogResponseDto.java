package com.ssafy.jupging.dto;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.LikeLog;
import com.ssafy.jupging.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeLogResponseDto {

    private Long articleId;
    private String nickname;
    private String profilePath;
    private LocalDateTime createdDate;

    public LikeLogResponseDto(LikeLog likeLog, Article article, User user){
        this.articleId=article.getArticleId();
        this.nickname= user.getNickname();
        this.profilePath=user.getProfilePath();
        this.createdDate = likeLog.getCreatedDate();
    }
}
