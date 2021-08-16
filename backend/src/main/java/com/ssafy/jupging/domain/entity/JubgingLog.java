package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.JubgingLogSaveRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class JubgingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jubgingId;

    private String totalTime;

    private String distance;

    private Long userId;

    private LocalDateTime createdDate;

    private String address;

    public JubgingLog saveJubgingLog(JubgingLogSaveRequestDto requestDto) {
        JubgingLog result = new JubgingLog();
        result.totalTime = requestDto.getTotalTime();
        result.distance = requestDto.getDistance();
        result.userId = requestDto.getUserId();
        result.createdDate = LocalDateTime.now();
        String[] addArr = requestDto.getAddress().split(" ");
        String add1 = addArr[0];
        String add2 = addArr[1];
        result.address = add1 + " " + add2;
        return result;
    }
}
