package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.JubgingLog;
import com.ssafy.jupging.dto.JubgingLogSaveRequestDto;
import com.ssafy.jupging.service.JubgingLogService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/jubginglog")
public class JubgingLogController {

    private final JubgingLogService jubgingLogService;
    private final UserService userService;

    @ApiOperation(value = "줍깅 정보 저장하기", notes = "성공 시 '줍깅로그 저장 성공' / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveJubgingLog(@RequestBody JubgingLogSaveRequestDto requestDto) {
        ControllerResponse response = null;
        try {
            JubgingLog jubgingLog = new JubgingLog();
            jubgingLogService.saveJubgingLog(requestDto);

            // 유저 score 저장
            int score = 100;
            int disScore = Integer.parseInt(requestDto.getDistance()) * 100;
            String[] time = requestDto.getTotalTime().split(":");
            int timeScore = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            score += disScore + timeScore;
            userService.updateScore(requestDto.getUserId(), score);

            response = new ControllerResponse("success", "줍깅로그 저장 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "유저 줍깅로그 가져오기", notes = "데이터가 있으면 리스트 반환, 없으면 null 반환", response = ControllerResponse.class)
    @GetMapping("/{id}")
    public ControllerResponse findUserJubgingLog(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<JubgingLog> list = jubgingLogService.findUserJubgingLog(userId);
            if (list.isEmpty()) {
                response = new ControllerResponse("success", null);
            } else {
                response = new ControllerResponse("success", list);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "줍깅 정보 삭제하기", notes = "성공 시 '줍깅로그 삭제 성공' / 실패 시 에러메세지", response = ControllerResponse.class)
    @DeleteMapping("/{id}")
    public ControllerResponse deleteJubgingLog(@PathVariable("id") Long jubgingId) {
        ControllerResponse response = null;
        try {
            jubgingLogService.deleteJubgingLog(jubgingId);
            response = new ControllerResponse("success", "줍깅로그 삭제 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "전체 줍깅 카운트 가져오기", notes = "오늘 줍깅 수 반환", response = ControllerResponse.class)
    @GetMapping("/total")
    public ControllerResponse countJubgingLog() {
        ControllerResponse response = null;
        try {
            LocalDateTime startDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(00,00,00));
            LocalDateTime endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));

            int todayCount = jubgingLogService.countJubgingLog(startDate, endDate);

            response = new ControllerResponse("success", todayCount);

        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

}
