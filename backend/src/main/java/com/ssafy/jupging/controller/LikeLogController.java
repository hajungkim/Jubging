package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.LikeLog;
import com.ssafy.jupging.dto.LikeLogRequestDto;
import com.ssafy.jupging.service.LikeLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/likelog")
public class LikeLogController {

    private final LikeLogService likeLogService;

    /**
     * 좋아요 등록, save
     * @param requestDto
     * @return
     */
    @PostMapping
    public ControllerResponse saveLikeLog(@RequestBody LikeLogRequestDto requestDto){
        ControllerResponse response = null;

        try{
            LikeLog likeLog = LikeLog.saveLikeLog(requestDto);
            likeLogService.saveLikeLog(likeLog);

            response = new ControllerResponse("success", "좋아요 등록 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", "좋아요 등록 실패");
        }

        return response;
    }

    /**
     * 좋아요 취소
     * @param requestDto
     * @return
     */
    @DeleteMapping
    public ControllerResponse deleteLikeLog(@RequestBody LikeLogRequestDto requestDto){
        ControllerResponse response = null;

        try{
            likeLogService.deleteLikeLog(requestDto.getUserId(), requestDto.getArticleId());
            response = new ControllerResponse("success", "좋아요 취소 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", "좋아요 취소 실패");
        }

        return response;
    }

    /**
     * 사용자가 누른 좋아요 목록을 반환
     * @param user_id
     * @return
     */
    @GetMapping("/{user_id}")
    public ControllerResponse findLikeLog(@PathVariable long user_id){
        ControllerResponse response = null;
        try{
            List<LikeLog> likeLogList = likeLogService.findLikeLog(user_id);


            response = new ControllerResponse("success", likeLogList);
        }catch (Exception e){
            response = new ControllerResponse("fail", "좋아요 취소 실패");
        }

        return response;
    }

    /**
     * 해당 게시글id 에 좋아요를 누른 사람들의 리스트
     * @param article_id
     * @return
     */
    @GetMapping("/likelist/{article_id}")
    public ControllerResponse findUserLikeList(@PathVariable long article_id){
        ControllerResponse response = null;
        try{
            List<LikeLog> likeLogList = likeLogService.findUserLikeList(article_id);

            response = new ControllerResponse("success", likeLogList);
        }catch (Exception e){
            response = new ControllerResponse("fail", "좋아요 취소 실패");
        }

        return response;
    }

}
