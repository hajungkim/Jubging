package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {

    private String token;

    private Long userId;

    public UserResponseDto saveTokenUserId(String token, Long userId) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.token = token;
        userResponseDto.userId = userId;
        return userResponseDto;
    }
}
