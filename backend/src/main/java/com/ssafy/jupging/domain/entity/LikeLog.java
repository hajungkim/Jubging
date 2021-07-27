package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.LikeLogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class LikeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long articleId;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp createdDate;

    public static LikeLog saveLikeLog(LikeLogRequestDto requestDto){
        LikeLog likeLog = new LikeLog();
        likeLog.userId=requestDto.getUserId();
        likeLog.articleId= requestDto.getArticleId();

        return likeLog;
    }
}
