package com.ssafy.jupging.domain.entity;

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
    private int glassCnt;
    private int paperCnt;
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

}
