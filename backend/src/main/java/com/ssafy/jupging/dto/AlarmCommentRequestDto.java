package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmCommentRequestDto {

    private Long userId;

    private Long pubId;

    private Long articleId;

    private String nickname;

    private String profilePath;

    private String category;

}
