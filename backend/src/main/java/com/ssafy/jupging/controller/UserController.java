package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.UserSaveRequestDto;
import com.ssafy.jupging.dto.UserUpdateRequestDto;
import com.ssafy.jupging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.Control;

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

    @GetMapping("/find/{id}")
    public ControllerResponse findUser(@PathVariable("id") Long userId) {
        ControllerResponse response = null;

        try {
            User result = userService.findUser(userId);
            response = new ControllerResponse("success", result);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @PutMapping("/update/{id}")
    public ControllerResponse updateUser(@PathVariable("id") Long userId, @RequestBody UserUpdateRequestDto requestDto) {
        ControllerResponse response = null;

        try {
            userService.updateUser(userId, requestDto);
            response = new ControllerResponse("success", "회원정보를 수정했습니다.");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "업데이트 중 에러발생");
        }

        return response;
    }

    @DeleteMapping("/delete/{id}")
    public ControllerResponse deleteUser(@PathVariable("id") Long userId) {
        ControllerResponse response = null;

        try {
            userService.deleteUser(userId);
            response = new ControllerResponse("success", "회원정보를 삭제했습니다.");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "회원정보 삭제 실패");
        }

        return response;
    }

}
