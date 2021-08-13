package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Map;
import com.ssafy.jupging.service.MapService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/map")
public class MapController {

    private final MapService mapService;

    @ApiOperation(value = "지번주소 받아와서 줍깅카운트 up 시키기", notes = "성공 시 '지역 카운트 up 성공'", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse updateCountMap(@RequestParam String address) {
        ControllerResponse response = null;

        try {
            String[] addArr = address.split(" ");
            String add1 = addArr[0];
            String add2 = addArr[1];
            mapService.updateCountMap(add1, add2);
            response = new ControllerResponse("success", "지역 카운트 up 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "지역,카운트 전체 리스트", notes = "성공 시 지역이름, 줍깅카운트 리스트 반환", response = ControllerResponse.class)
    @GetMapping
    public ControllerResponse findAllMap() {
        ControllerResponse response = null;

        try {
            List<Map> mapList = mapService.findAllMap();
            response = new ControllerResponse("success", mapList);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
