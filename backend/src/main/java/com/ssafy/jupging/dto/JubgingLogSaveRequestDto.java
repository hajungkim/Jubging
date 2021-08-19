package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JubgingLogSaveRequestDto {

    private String totalTime;

    private String distance;

    private Long userId;

    private String address;

}
