package com.ssafy.jupging.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MissionUpdateRequestDto {

    private Long userId;

    private boolean can;
    private boolean plastic;
    private boolean bottle;
    private boolean paper;
    private boolean styroform;
    private boolean metal;
    private boolean vinyl;
    private boolean trash;

    private boolean mountain;
    private boolean ocean;
    private boolean river;
    private boolean arround;

    private String distance;

}
