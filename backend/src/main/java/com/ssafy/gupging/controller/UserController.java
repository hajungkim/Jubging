package com.ssafy.gupging.controller;

import com.ssafy.gupging.domain.entity.User;
import com.ssafy.gupging.dto.UserSaveRequestDto;
import com.ssafy.gupging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ControllerResponse saveUser(@RequestBody UserSaveRequestDto requestDto) {
        ControllerResponse response = null;

        try {
            User user = User.saveUser(requestDto);
            userService.save(user);

            response = new ControllerResponse("success", "회원가입에 성공했습니다.");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "회원가입에 실패했습니다.");
        }

        return response;
    }

}
