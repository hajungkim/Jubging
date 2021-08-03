package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSearchResponseDto implements Comparable<UserSearchResponseDto> {

    private Long userId;

    private String nickname;

    private int follower;

    private String profilePath;

    public void save(Long userId, String nickname, int follower, String profilePath) {
        this.userId = userId;
        this.nickname = nickname;
        this.follower = follower;
        this.profilePath = profilePath;
    }

    @Override
    public int compareTo(UserSearchResponseDto o) {
        if (this.nickname.length() == o.getNickname().length()) {
            return o.follower - this.follower;
        } else {
            return this.nickname.length() - o.nickname.length();
        }
    }
}
