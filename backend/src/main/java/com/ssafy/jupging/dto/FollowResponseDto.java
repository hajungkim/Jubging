package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowResponseDto {

    private Long followUserId;

    private String nickName;

    public void saveIdAndNick(Long followUserId, String nickName) {
        this.followUserId = followUserId;
        this.nickName = nickName;
    }

}
