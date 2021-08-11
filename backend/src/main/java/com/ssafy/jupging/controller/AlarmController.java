package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.AlarmCommentRequestDto;
import com.ssafy.jupging.service.AlarmService;
import com.ssafy.jupging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class AlarmController {

    private final SimpMessageSendingOperations template;
    private final AlarmService alarmService;
    private final UserService userService;

    @MessageMapping("/pub/{userId}")
    public void SocketHandler(@DestinationVariable("userId") Long userId, AlarmCommentRequestDto requestDto) {
        String msg = "";

        try {
            alarmService.saveAlarm(requestDto);
            msg = requestDto.getNickname() + "님이 댓글을 남겼습니다.";
        } catch (Exception e) {
            msg = e.getMessage();
        }

        template.convertAndSend("/sub/" + userId, msg);
    }

}
