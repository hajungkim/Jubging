package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.LikeLogRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private LocalDateTime createdDate;

    public static LikeLog saveLikeLog(LikeLogRequestDto requestDto){
        LikeLog likeLog = new LikeLog();
        likeLog.userId=requestDto.getUserId();
        likeLog.articleId= requestDto.getArticleId();

        return likeLog;
    }
}
