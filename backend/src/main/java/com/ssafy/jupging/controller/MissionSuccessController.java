package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Mission;
import com.ssafy.jupging.domain.entity.MissionSuccess;
import com.ssafy.jupging.dto.MissionSuccessRequestDto;
import com.ssafy.jupging.dto.MissionUpdateRequestDto;
import com.ssafy.jupging.service.MissionService;
import com.ssafy.jupging.service.MissionSuccessService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/missionsuccess")
public class MissionSuccessController {

    private final MissionSuccessService missionSuccessService;

    @ApiOperation(value = "성공미션 조회", notes = "성공 시 미션객체 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/{id}")
    public ControllerResponse findUserMission(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            MissionSuccess missionSuccess = new MissionSuccess();
            missionSuccess = missionSuccessService.findUserMission(userId);

            response = new ControllerResponse("success", missionSuccess);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "성공미션 업데이트", notes = "성공 시 '미션 업데이트 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PutMapping
    public ControllerResponse updateMission(@RequestBody MissionSuccessRequestDto requestDto) {
        ControllerResponse response = null;
        try {
            missionSuccessService.updateMission(requestDto);

            response = new ControllerResponse("success", "미션 업데이트 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

}
