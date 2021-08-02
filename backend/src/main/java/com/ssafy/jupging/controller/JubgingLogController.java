package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.JubgingLog;
import com.ssafy.jupging.dto.JubgingLogSaveRequestDto;
import com.ssafy.jupging.service.JubgingLogService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jubginglog")
public class JubgingLogController {

    private final JubgingLogService jubgingLogService;

    @ApiOperation(value = "줍깅 정보 저장하기", notes = "성공 시 '줍깅로그 저장 성공' / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveJubgingLog(@RequestBody JubgingLogSaveRequestDto requestDto) {
        ControllerResponse response = null;
        try {
            JubgingLog jubgingLog = new JubgingLog();
            jubgingLog = jubgingLog.saveJubgingLog(requestDto);
            jubgingLogService.saveJubgingLog(jubgingLog);

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

}
