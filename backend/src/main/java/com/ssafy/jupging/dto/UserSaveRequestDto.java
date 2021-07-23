package com.ssafy.jupging.dto;
//유저 회원가입 요청 dto

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequestDto {

    private String email;
    private String nickname;
    private String password;

}
