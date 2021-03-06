package com.ssafy.jupging.domain.entity;

import com.ssafy.jupging.dto.MissionUpdateRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Mission {

    @Id
    private Long userId;

    @Column(name="can_cnt")
    private int can;
    @Column(name="plastic_cnt")
    private int plastic;
    @Column(name="bottle_cnt")
    private int bottle;
    @Column(name="paper_cnt")
    private int paper;
    @Column(name="styroform_cnt")
    private int styroform;
    @Column(name="metal_cnt")
    private int metal;
    @Column(name="vinyl_cnt")
    private int vinyl;
    @Column(name="trash_cnt")
    private int trash;

    @Column(name="mountain_cnt")
    private int mountain;
    @Column(name="ocean_cnt")
    private int ocean;
    @Column(name="river_cnt")
    private int river;
    @Column(name="arround_cnt")
    private int arround;

    @Column(name="like_cnt")
    private int like;
    @Column(name="follow_cnt")
    private int follow;
    @Column(name="comment_cnt")
    private int comment;
    @Column(name="article_cnt")
    private int article;
    @Column(name="jubging_cnt")
    private int jubging;

    @Column(name="total_distance")
    private double distance;

    public Mission saveInit(Long userId) {
        Mission mission = new Mission();
        mission.userId = userId;
        return mission;
    }

    public void updateMission(MissionUpdateRequestDto requestDto) {
        if (requestDto.isCan()) this.can++;
        if (requestDto.isPlastic()) this.plastic++;
        if (requestDto.isBottle()) this.bottle++;
        if (requestDto.isPaper()) this.paper++;
        if (requestDto.isStyroform()) this.styroform++;
        if (requestDto.isMetal()) this.metal++;
        if (requestDto.isVinyl()) this.vinyl++;
        if (requestDto.isTrash()) this.trash++;
        if (requestDto.isMountain()) this.mountain++;
        if (requestDto.isOcean()) this.ocean++;
        if (requestDto.isRiver()) this.river++;
        if (requestDto.isArround()) this.arround++;
        this.jubging++;
        this.distance += Double.parseDouble(requestDto.getDistance());
    }

    public void updateFollowMission(boolean isFollow) {
        if (isFollow) {
            this.follow++;
        } else {
            if (this.follow != 0) {
                this.follow--;
            }
        }
    }

    public void updateArticleMission(boolean isArticle) {
        if (isArticle) {
            this.article++;
        } else {
            if (this.article != 0) {
                this.article--;
            }
        }
    }

    public void updateLikeMission(boolean isLike) {
        if (isLike) {
            this.like++;
        } else {
            if (this.like != 0) {
                this.like--;
            }
        }
    }

    public void updateCommentMission(boolean isComment) {
        if (isComment) {
            this.comment++;
        } else {
            if (this.comment != 0) {
                this.comment--;
            }
        }
    }


}
