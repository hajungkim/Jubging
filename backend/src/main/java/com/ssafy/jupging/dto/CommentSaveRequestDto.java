package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequestDto {
    private Long articleId;
    private Long userId;
    private String commentContent;
}
