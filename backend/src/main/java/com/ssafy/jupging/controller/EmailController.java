package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.service.EmailService;
import com.ssafy.jupging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/auth/{email}")
    public ControllerResponse sendAuthEmail(@PathVariable String email){
        ControllerResponse response = null;

        if(!userService.checkEmail(email))
            return new ControllerResponse("fail", "이미 존재하는 이메일");

        emailService.sendAuthEmail(email);
        response = new ControllerResponse("success", "인증 메일 전송 성공");

        return response;
    }

    @PostMapping("/changepw/{userId}")
    public ControllerResponse sendTempPwEmail(@PathVariable Long userId){
        ControllerResponse response = null;

        User user = userService.findUser(userId);

        emailService.sendTempPwEmail(user);
        response = new ControllerResponse("success", "임시 비밀번호 메일 전송 성공");

        return response;
    }


}
