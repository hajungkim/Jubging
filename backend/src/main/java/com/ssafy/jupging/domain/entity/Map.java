package com.ssafy.jupging.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Map {

    @Id
    private String code;

    private String name;

    private String count;

    public void updateMap() {
        int intCount = Integer.parseInt(this.count);
        this.count = Integer.toString(++intCount);
    }

}
