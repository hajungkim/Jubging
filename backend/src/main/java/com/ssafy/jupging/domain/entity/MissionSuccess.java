package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.MissionSuccessRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class MissionSuccess {

    @Id
    private Long userId;

    private int distanceBronze;
    private int distanceSilver;
    private int distanceGold;
    private int plasticBronze;
    private int plasticSilver;
    private int plasticGold;
    private int canBronze;
    private int canSilver;
    private int canGold;

    public MissionSuccess saveInit(Long userId) {
        MissionSuccess missionSuccess = new MissionSuccess();
        missionSuccess.userId = userId;
        return missionSuccess;
    }

    public void updateMissionSuccess(MissionSuccessRequestDto requestDto) {
        if (requestDto.isDistanceBronze()) this.distanceBronze = 1;
        if (requestDto.isDistanceSilver()) this.distanceSilver = 1;
        if (requestDto.isDistanceGold()) this.distanceGold = 1;
        if (requestDto.isPlasticBronze()) this.canBronze = 1;
        if (requestDto.isPlasticSilver()) this.plasticSilver = 1;
        if (requestDto.isPlasticGold()) this.plasticGold = 1;
        if (requestDto.isCanBronze()) this.canBronze = 1;
        if (requestDto.isCanSilver()) this.canSilver = 1;
        if (requestDto.isCanGold()) this.canGold = 1;
    }

}
