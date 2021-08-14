package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Event;
import com.ssafy.jupging.service.EventService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @ApiOperation(value = "플로깅 이벤트 리스트", notes = "성공 시 이벤트 리스트 반환", response = ControllerResponse.class)
    @GetMapping
    public ControllerResponse findAllEvent() {
        ControllerResponse response = null;

        try {
            List<Event> eventList = eventService.findAllEvent();
            response = new ControllerResponse("success", eventList);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }
}
