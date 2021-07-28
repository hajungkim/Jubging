package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FollowerResponseDto {

    private Long followerUserId;

    private String nickName;

    public void saveIdAndNick(Long followerUserId, String nickName) {
        this.followerUserId = followerUserId;
        this.nickName = nickName;
    }

}
