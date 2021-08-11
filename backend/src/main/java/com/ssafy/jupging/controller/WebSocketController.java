package com.ssafy.jupging.controller;

import com.ssafy.jupging.dto.AlarmCommentRequestDto;
import com.ssafy.jupging.service.AlarmService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class WebSocketController {

    private final SimpMessageSendingOperations template;
    private final AlarmService alarmService;

    @MessageMapping("/pub/{userId}")
    public void CommentSocketHandler(@DestinationVariable("userId") Long userId, AlarmCommentRequestDto requestDto) {
        String msg = "";

        try {
            alarmService.saveAlarm(requestDto);
            if (requestDto.getCategory().equals("comment")) {
                System.out.println("ok");
                msg = requestDto.getNickname() + "님이 게시글에 댓글을 남겼습니다.";
            } else if (requestDto.getCategory().equals("like")) {
                msg = requestDto.getNickname() + "님이 게시글에 좋아요를 눌렀습니다.";
            } else if (requestDto.getCategory().equals("follow")) {
                msg = requestDto.getNickname() + "님이 회원님을 팔로우 했습니다.";
            }
        } catch (Exception e) {
            msg = e.getMessage();
        }

        template.convertAndSend("/sub/" + userId, msg);
    }
}
