package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleSaveRequestDto {
    private String content;
    private int user_id;
}
