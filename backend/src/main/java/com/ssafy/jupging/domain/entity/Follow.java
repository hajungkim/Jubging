package com.ssafy.jupging.domain.entity;

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
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    private Long userId;

    private Long followUserId;

    private LocalDateTime followDate;

    public Follow saveFollow(Long userId, Long followId) {
        Follow result = new Follow();
        result.userId = userId;
        result.followId = followId;
        result.followDate = LocalDateTime.now();
        return result;
    }

}
