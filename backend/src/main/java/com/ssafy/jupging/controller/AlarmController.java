package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Alarm;
import com.ssafy.jupging.dto.AlarmResponseDto;
import com.ssafy.jupging.service.AlarmService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @ApiOperation(value = "유저의 알람목록리스트", notes = "성공 시 댓글 리스트 반환 / 없으면 null 반환", response = ControllerResponse.class)
    @GetMapping("/{userId}")
    public ControllerResponse findAllAlarm(@PathVariable("userId") Long userId) {
        ControllerResponse response = null;

        try {
            List<Alarm> list = alarmService.findAllAlarm(userId);
            if (list.size() == 0) return new ControllerResponse("success", null);
            List<AlarmResponseDto> responseDtoList = new ArrayList<>();
            for (Alarm alarm : list) {
                String time = commentTimeFormat(alarm.getCreatedDate());
                AlarmResponseDto responseDto
                        = new AlarmResponseDto(alarm.getPubId(), alarm.getArticleId(), alarm.getNickname(), alarm.getProfilePath(), alarm.getCategory(), time);
                responseDtoList.add(responseDto);
            }
            response = new ControllerResponse("success", responseDtoList);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    private static class TIME_VALUE{
        public static final int SEC = 60;
        public static final int MIN = 60;
        public static final int HOUR = 24;
        public static final int DAY = 30;
        public static final int MONTH = 12;
    }

    public String commentTimeFormat(LocalDateTime createdDate){
        long curTime = System.currentTimeMillis();
        long regTime = java.sql.Timestamp.valueOf(createdDate).getTime();
        long diff = (curTime - regTime) / 1000;

        String msg = "";

        if (diff < AlarmController.TIME_VALUE.SEC) {
            msg = "방금전";
        } else if ((diff /= AlarmController.TIME_VALUE.SEC) < AlarmController.TIME_VALUE.MIN) {
            msg = diff + "분전";
        } else if ((diff /= AlarmController.TIME_VALUE.MIN) < AlarmController.TIME_VALUE.HOUR) {
            msg = (diff) + "시간전";
        } else if ((diff /= AlarmController.TIME_VALUE.HOUR) < AlarmController.TIME_VALUE.DAY) {
            msg = (diff) + "일전";
        } else if ((diff /= AlarmController.TIME_VALUE.DAY) < AlarmController.TIME_VALUE.MONTH) {
            msg = (diff) + "달전";
        } else {
            msg = (diff) + "년전";
        }
        return msg;
    }

}
