package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionSuccessRequestDto {

    private Long userId;
    private boolean distanceBronze;
    private boolean distanceSilver;
    private boolean distanceGold;
    private boolean plasticBronze;
    private boolean plasticSilver;
    private boolean plasticGold;
    private boolean canBronze;
    private boolean canSilver;
    private boolean canGold;

}
