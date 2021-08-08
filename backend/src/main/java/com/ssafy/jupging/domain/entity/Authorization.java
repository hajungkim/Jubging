package com.ssafy.jupging.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Authorization {
    @Id
    @Column(length = 500 , nullable = false)
    private String email;

    @Column(length = 5 , nullable = false)
    private String authKey;

    public void saveAuthorization(String email, String authKey) {
        this.email=email;
        this.authKey=authKey;
    }
}
