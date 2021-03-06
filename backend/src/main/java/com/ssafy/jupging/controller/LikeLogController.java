package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.LikeLog;
import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.LikeLogRequestDto;
import com.ssafy.jupging.dto.LikeLogResponseDto;
import com.ssafy.jupging.service.ArticleService;
import com.ssafy.jupging.service.LikeLogService;
import com.ssafy.jupging.service.MissionService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/likelog")
public class LikeLogController {

    private final LikeLogService likeLogService;
    private final ArticleService articleService;
    private final UserService userService;
    private final MissionService missionService;

    /**
     * 좋아요 등록, save
     * @param requestDto
     * @return
     */
    @ApiOperation(value = "좋아요 등록/취소", notes = "성공 시 '좋아요 등록 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveLikeLog(@RequestBody LikeLogRequestDto requestDto){
        ControllerResponse response = null;

        try{
            boolean like =true;
            Optional<LikeLog> likeOX = likeLogService.findLikeLog(requestDto.getUserId(), requestDto.getArticleId());
            if(likeOX.isPresent()){ //이미 좋아요를 누른 기록이 있을 경우
                like =false;
            }

            if (like == true) {
                //좋아요 로그 기록
                LikeLog likeLog = LikeLog.saveLikeLog(requestDto);
                likeLogService.saveLikeLog(likeLog);
            } else {
                likeLogService.deleteLikeLog(requestDto.getUserId(), requestDto.getArticleId());
            }

            //좋아요 cnt 값 반영
            Article article = articleService.findByArticleId(requestDto.getArticleId());
            int cnt = article.getLikeCnt();
            likeLogService.updateLikecnt(like, cnt, requestDto.getArticleId());

            //좋아요 미션 카운트+1
            missionService.updateLikeMission(requestDto.getUserId(), like);

            response = new ControllerResponse("success", "좋아요 반영 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 사용자가 누른 좋아요 목록을 반환
     * @param user_id
     * @return 리스트 - LikeLog 객체 + Artocle 객체
     */
    @ApiOperation(value = "좋아요 로그 찾기(최신순 정렬)", notes = "성공 시 사용자가 누른 좋아요 리스트 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/{user_id}")
    public ControllerResponse findLikeLog(@PathVariable Long user_id){
        ControllerResponse response = null;
        try{
            List<LikeLog> likeLogList = likeLogService.findLikeLog(user_id);
            List<LikeLogResponseDto> list = new ArrayList<>();
            for(LikeLog likeLog : likeLogList){
                Article article = articleService.findByArticleId(likeLog.getArticleId());
                User user = userService.findUser(article.getUserId());
                list.add(new LikeLogResponseDto(likeLog, article, user));
            }

            response = new ControllerResponse("success", list);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 해당 게시글id 에 좋아요를 누른 사람들의 리스트
     * @param article_id
     * @return 리스트 - {nickname, profilepath}
     */
    @ApiOperation(value = "좋아요 누른 사람 찾기", notes = "성공 시 해당 게시글id 에 좋아요를 누른 사람들의 리스트 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/likelist/{article_id}")
    public ControllerResponse findUserLikeList(@PathVariable long article_id){
        ControllerResponse response = null;

        try{
            List<LikeLog> likeLogList = likeLogService.findUserLikeList(article_id);
            List<LikeLogResponseDto> list = new ArrayList<>();
            for(LikeLog likeLog : likeLogList){
                User user = userService.findUser(likeLog.getUserId());
                LikeLogResponseDto responseDto = new LikeLogResponseDto(likeLog, user, likeLog.getArticleId());
                list.add(responseDto);
            }
            response = new ControllerResponse("success", list);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
