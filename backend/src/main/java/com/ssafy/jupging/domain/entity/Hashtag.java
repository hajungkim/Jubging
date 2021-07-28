package com.ssafy.jupging.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashId;

    @Column(length =100, nullable = false)
    private String content;

    @Column(nullable = false)
    private Long articleId;

}
