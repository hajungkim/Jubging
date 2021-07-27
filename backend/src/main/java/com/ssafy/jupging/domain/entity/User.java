package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.UserSaveRequestDto;
import com.ssafy.jupging.dto.UserUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private String nickname;

    private String password;

    private int score;

    private String profilePath;

    private int articleCount;

    private LocalDateTime createdDate;

    public User saveUser(UserSaveRequestDto requestDto) {
        User result = new User();
        result.email = requestDto.getEmail();
        result.nickname = requestDto.getNickname();
        result.password = requestDto.getPassword();
        result.createdDate = LocalDateTime.now();
        return result;
    }

    public void updateUser(UserUpdateRequestDto requestDto) {
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getPassword();
    }
}
