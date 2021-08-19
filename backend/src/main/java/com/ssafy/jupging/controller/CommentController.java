package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.dto.CommentResponseDto;
import com.ssafy.jupging.dto.CommentSaveRequestDto;
import com.ssafy.jupging.service.CommentService;
import com.ssafy.jupging.service.MissionService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;
    private final MissionService missionService;

    @ApiOperation(value = "댓글 등록", notes = "성공 시 '댓글 등록 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveComment(@RequestBody CommentSaveRequestDto commentSaveRequestDto){
        ControllerResponse response = null;

        try{
            Comment comment = Comment.saveComment(commentSaveRequestDto);
            commentService.saveComment(comment);

            //댓글 미션 +1
            missionService.updateCommentMission(commentSaveRequestDto.getUserId(), true);

            response = new ControllerResponse("success", "댓글 등록 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "댓글 삭제", notes = "성공 시 '댓글 삭제 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @DeleteMapping("/{comment_id}")
    public ControllerResponse deleteComment(@PathVariable long comment_id, @RequestParam Long userId){
        ControllerResponse response = null;

        try{
            commentService.deleteComment(comment_id);

            //댓글 미션 +1
            missionService.updateCommentMission(userId, false);

            response = new ControllerResponse("success", "댓글 삭제 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "게시글의 댓글 찾기", notes = "성공 시 댓글 리스트 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/{article_id}")
    public ControllerResponse findComment(@PathVariable long article_id){
        ControllerResponse response = null;

        try{
            List<Comment> commentList = commentService.findAllComment(article_id);
            List<CommentResponseDto> list = new ArrayList<>();
            for(Comment comment : commentList){
                CommentResponseDto commentResponseDto = new CommentResponseDto(comment, userService.findUser(comment.getUserId()));
                commentResponseDto.setTime(commentTimeFormat(comment.getCreatedDate()));
                list.add(commentResponseDto);
            }

            response = new ControllerResponse("success", list);
        }catch (Exception e){
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

    public String commentTimeFormat(Date createdDate){
        long curTime = System.currentTimeMillis();
        long regTime = createdDate.getTime();
        long diff = (curTime - regTime) / 1000;

        String msg = "";

        if (diff < TIME_VALUE.SEC) {
            msg = "방금전";
        } else if ((diff /= TIME_VALUE.SEC) < TIME_VALUE.MIN) {
            msg = diff + "분전";
        } else if ((diff /= TIME_VALUE.MIN) < TIME_VALUE.HOUR) {
            msg = (diff) + "시간전";
        } else if ((diff /= TIME_VALUE.HOUR) < TIME_VALUE.DAY) {
            msg = (diff) + "일전";
        } else if ((diff /= TIME_VALUE.DAY) < TIME_VALUE.MONTH) {
            msg = (diff) + "달전";
        } else {
            msg = (diff) + "년전";
        }
        return msg;
    }
}
