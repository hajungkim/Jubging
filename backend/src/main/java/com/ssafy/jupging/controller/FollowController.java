package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Follow;
import com.ssafy.jupging.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;

    @PostMapping("/save")
    public ControllerResponse saveFollow(@RequestParam Long userId, @RequestParam Long followUserId) {
        ControllerResponse response = null;
        try {
            Follow follow = new Follow();
            follow.saveFollow(userId, followUserId);
            followService.saveFollow(follow);

            response = new ControllerResponse("success", "팔로우 등록 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "팔로우 등록 실패");
        }
        return response;
    }

    //개인피드에 자신이 팔로우하는 리스트 반환하기
    @GetMapping("/findFollow/{id}")
    public ControllerResponse findFollow(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<Follow> followList = followService.findFollow(userId);
            if (followList.isEmpty()) {
                response = new ControllerResponse("success", null);
            } else {
                response = new ControllerResponse("success", followList);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    //개인피드에 자신의 팔로워 리스트 반환하기
    @GetMapping("/findFollower/{id}")
    public ControllerResponse findFollower(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<Follow> followerList = followService.findFollower(userId);
            if (followerList.isEmpty()) {
                response = new ControllerResponse("success", null);
            } else {
                response = new ControllerResponse("success", followerList);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

}
