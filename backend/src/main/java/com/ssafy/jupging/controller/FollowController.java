package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Follow;
import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.FollowResponseDto;
import com.ssafy.jupging.dto.FollowerResponseDto;
import com.ssafy.jupging.service.FollowService;
import com.ssafy.jupging.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {

    private final FollowService followService;
    private final UserService userService;

    @PostMapping("/save")
    public ControllerResponse saveFollow(@RequestParam Long userId, @RequestParam Long followUserId) {
        ControllerResponse response = null;
        try {
            Follow follow = new Follow();
            follow = follow.saveFollow(userId, followUserId);
            followService.saveFollow(follow);

            response = new ControllerResponse("success", "팔로우 등록 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "팔로우 등록 실패");
        }
        return response;
    }

    //개인피드에 자신이 팔로우하는 리스트(팔로우 유저 아이디, 팔로우 닉네임) 반환하기
    @GetMapping("/findfollow/{id}")
    public ControllerResponse findFollow(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<Follow> followList = followService.findFollow(userId);
            if (followList.isEmpty()) {
                response = new ControllerResponse("success", null);
            } else {
                List<FollowResponseDto> followResponseDtoList = new ArrayList<>();
                for (Follow foll : followList) {
                    User user = userService.findUser(foll.getFollowUserId());
                    FollowResponseDto followResponseDto = new FollowResponseDto();
                    followResponseDto.saveIdAndNick(user.getUserId(), user.getNickname());
                    followResponseDtoList.add(followResponseDto);
                }

                response = new ControllerResponse("success", followResponseDtoList);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    //개인피드에 자신의 팔로워 리스트(팔로워 유저 아이디, 팔로워 닉네임) 반환하기
    @GetMapping("/findfollower/{id}")
    public ControllerResponse findFollower(@PathVariable("id") Long userId) {
        ControllerResponse response = null;
        try {
            List<Follow> followerList = followService.findFollower(userId);
            if (followerList.isEmpty()) {
                response = new ControllerResponse("success", null);
            } else {
                List<FollowerResponseDto> followerResponseDtoList = new ArrayList<>();
                for (Follow foll : followerList) {
                    User user = userService.findUser(foll.getUserId());
                    FollowerResponseDto followerResponseDto = new FollowerResponseDto();
                    followerResponseDto.saveIdAndNick(user.getUserId(), user.getNickname());
                    followerResponseDtoList.add(followerResponseDto);
                }

                response = new ControllerResponse("success", followerResponseDtoList);
            }
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public ControllerResponse deleteFollow(@RequestParam Long userId, @RequestParam Long followUserId) {
        ControllerResponse response = null;
        try {
            followService.deleteByUserIdAndFollowUserId(userId, followUserId);

            response = new ControllerResponse("success", "팔로우 삭제 성공");
        } catch (Exception e) {
            response = new ControllerResponse("fail", "팔로우 삭제 실패");
        }
        return response;
    }

}
