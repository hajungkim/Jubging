package com.ssafy.jupging.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor
@Entity
public class JubgingLogController {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jubgingId;

    private String totalTime;

    private String distance;

    private Long userId;

    private Timestamp createdDate;

}
