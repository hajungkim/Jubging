package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowResponseDto {

    private Long userId;

    private String nickName;

    private String profilePath;

    public void saveIdAndNick(Long userId, String nickName, String profilePath) {
        this.userId = userId;
        this.nickName = nickName;
        this.profilePath = profilePath;
    }

}
