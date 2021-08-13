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

    private int count;

    public void updateMap() {
        this.count++;
    }

}
