package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRankingResponseDto implements Comparable<UserRankingResponseDto> {

    private Long userId;
    private String nickname;
    private int score;
    private String profilePath;

    @Override
    public int compareTo(UserRankingResponseDto o) {
        return o.score - this.score;
    }
}
