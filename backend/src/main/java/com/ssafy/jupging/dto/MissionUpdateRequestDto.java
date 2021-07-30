package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionUpdateRequestDto {

    private Long userId;

    private boolean canCnt;
    private boolean plasticCnt;
    private boolean bottleCnt;
    private boolean paperCnt;
    private boolean styroformCnt;
    private boolean metalCnt;
    private boolean vinylCnt;
    private boolean trashCnt;

    private boolean mountainCnt;
    private boolean oceanCnt;
    private boolean riverCnt;
    private boolean arroundCnt;

    private int jubgingCnt;
    private String totalDistance;

}
