package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.MissionUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Mission {

    @Id
    private Long userId;

    private int canCnt;
    private int plasticCnt;
    private int bottleCnt;
    private int paperCnt;
    private int styroformCnt;
    private int metalCnt;
    private int vinylCnt;
    private int trashCnt;

    private int mountainCnt;
    private int oceanCnt;
    private int riverCnt;
    private int arroundCnt;

    private int likeCnt;
    private int followCnt;
    private int followerCnt;
    private int commentCnt;
    private int articleCnt;
    private int jubgingCnt;

    private double totalDistance;

    public Mission saveInit(Long userId) {
        Mission mission = new Mission();
        mission.userId = userId;
        return mission;
    }

    public void updateMission(MissionUpdateRequestDto requestDto) {
        if (requestDto.isCanCnt()) this.canCnt++;
        if (requestDto.isPlasticCnt()) this.plasticCnt++;
        if (requestDto.isBottleCnt()) this.bottleCnt++;
        if (requestDto.isPaperCnt()) this.paperCnt++;
        if (requestDto.isStyroformCnt()) this.styroformCnt++;
        if (requestDto.isMetalCnt()) this.metalCnt++;
        if (requestDto.isVinylCnt()) this.vinylCnt++;
        if (requestDto.isTrashCnt()) this.trashCnt++;
        if (requestDto.isMountainCnt()) this.mountainCnt++;
        if (requestDto.isOceanCnt()) this.oceanCnt++;
        if (requestDto.isRiverCnt()) this.riverCnt++;
        if (requestDto.isArroundCnt()) this.arroundCnt++;
        this.jubgingCnt++;
        this.totalDistance += Double.parseDouble(requestDto.getTotalDistance());
    }

}
