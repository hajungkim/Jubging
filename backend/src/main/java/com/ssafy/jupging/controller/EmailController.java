package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.AuthorizationRequestDto;
import com.ssafy.jupging.service.EmailService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final UserService userService;
    private final EmailService emailService;

    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 전송 성공시 '전송 성공' 반환 / 실패 시 에러메시지", response = ControllerResponse.class)
    @PostMapping("/auth/{email}")
    public ControllerResponse sendAuthEmail(@PathVariable String email){
        ControllerResponse response = null;

        if (!userService.checkEmail(email)) {
            return new ControllerResponse("fail", "이미 존재하는 이메일");
        }

        try {
            emailService.sendAuthEmail(email);
            response = new ControllerResponse("success", "전송 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "이메일 인증번호가 맞는지 체크", notes = "성공시 '인증성공' 반환 / 실패 시 에러메시지", response = ControllerResponse.class)
    @PostMapping("/authcheck")
    public ControllerResponse checkAuthKey(@RequestBody AuthorizationRequestDto authorizationRequestDto){
        ControllerResponse response = null;

        try{
            String auth = emailService.checkAuthKey(authorizationRequestDto.getEmail());

            if (auth.equals(authorizationRequestDto.getAuthKey())) {
                response = new ControllerResponse("success", "인증 성공");
            }
            else {
                response = new ControllerResponse("fail", "인증 실패");
            }

        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "임시 비밀번호 발급", notes = "임시비밀번호 발급 및 이메일 전송 성공시 '전송 성공' 반환 / 실패 시 에러메시지", response = ControllerResponse.class)
    @PutMapping("/changepw/{userId}")
    public ControllerResponse sendTempPwEmail(@PathVariable Long userId){
        ControllerResponse response = null;

        User user = userService.findUser(userId);

        try{
            emailService.sendTempPwEmail(user);
            response = new ControllerResponse("success", "전송 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }


}
