package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeLogRequestDto {
    private Long userId;
    private Long articleId;
}
