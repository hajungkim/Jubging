package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.*;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    private String signature = "VUETOKEN";
    private Long expireMin = 5L;

    //로그인 성공 시 사용자 정보 기반으로 JWTToken 생성하여 반환
    public String create(User user) {
        JwtBuilder jwtBuilder = Jwts.builder();
        //Header 설정
        jwtBuilder.setHeaderParam("typ", "JWT");    //토큰 타입으로 고정값
        //Payload 설정
        jwtBuilder
                .setSubject("로그인 토큰")   //토큰 제목
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin))    //유효기간
                .claim("user", user).claim("greeting", "환영합니다 " + user.getNickname());  //담을 정보
        //signature 설정
        jwtBuilder.signWith(SignatureAlgorithm.HS256, signature.getBytes());

        //마지막 직렬화 처리
        String jwt = jwtBuilder.compact();
        return jwt;
    }

    public void checkValid(String jwt) {
        Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
    }

    public Map<String, Object> get(String jwt) {
        Jws<Claims> claims = null;
        try {
            claims = Jwts.parser().setSigningKey(signature.getBytes()).parseClaimsJws(jwt);
        } catch (final Exception e) {
            throw new RuntimeException();
        }

        return claims.getBody();
    }

}
