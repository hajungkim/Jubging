package com.ssafy.jupging.dto;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.LikeLog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeLogResponseDto {

    private LikeLog likeLog;
    private Article article;

}
