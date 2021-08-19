package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.AlarmCommentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long alarmId;

    private Long userId;

    private Long pubId;

    private Long articleId;

    private String nickname;

    private String profilePath;

    private String category;

    private LocalDateTime createdDate;

    public Alarm saveAlarm(AlarmCommentRequestDto requestDto) {
        Alarm alarm = new Alarm();
        alarm.userId = requestDto.getUserId();
        alarm.pubId = requestDto.getPubId();
        alarm.articleId = requestDto.getArticleId();
        alarm.category = requestDto.getCategory();
        alarm.nickname = requestDto.getNickname();
        alarm.profilePath = requestDto.getProfilePath();
        alarm.createdDate = LocalDateTime.now();
        return alarm;
    }
}
