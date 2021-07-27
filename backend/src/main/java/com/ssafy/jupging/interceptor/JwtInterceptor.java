package com.ssafy.jupging.interceptor;

import com.ssafy.jupging.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //option 요청은 통과
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        } else {
            String token = request.getHeader("login-token");
            System.out.println(token);
            if (token != null && token.length() > 0) {
                //유효한 토큰이면 true, 아니면 예외 발생
                jwtService.checkValid(token);
                return true;
            } else {
                throw new RuntimeException("인증 토큰이 없습니다.");
            }
        }
    }
}
