package com.ssafy.jupging.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JubgingLogResponseDto {

    private Long userId;
    private String distance;
    private String totalTime;
    private LocalDateTime createdDate;
    private String date;

}
