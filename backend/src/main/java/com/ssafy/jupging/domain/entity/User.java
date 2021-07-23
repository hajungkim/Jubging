package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.UserSaveRequestDto;
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

    public static User saveUser(UserSaveRequestDto request) {
        User result = new User();
        result.email = request.getEmail();
        result.nickname = request.getNickname();
        result.password = request.getPassword();
        result.createdDate = LocalDateTime.now();
        return result;
    }
}
