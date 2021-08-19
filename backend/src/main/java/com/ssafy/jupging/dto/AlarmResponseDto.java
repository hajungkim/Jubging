package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AlarmResponseDto {

    private Long pubId;

    private Long articleId;

    private String nickname;

    private String profilePath;

    private String category;

    private String time;
}
