package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.domain.repository.CommentRepository;
import com.ssafy.jupging.dto.CommentMapping;
import com.ssafy.jupging.dto.CommentResponseDto;
import com.ssafy.jupging.dto.CommentSaveRequestDto;
import com.ssafy.jupging.service.CommentService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @ApiOperation(value = "댓글 등록", notes = "성공 시 '댓글 등록 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveComment(@RequestBody CommentSaveRequestDto commentSaveRequestDto){
        ControllerResponse response = null;

        try{
            Comment comment = Comment.saveComment(commentSaveRequestDto);
            commentService.saveComment(comment);
            response = new ControllerResponse("success", "댓글 등록 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "댓글 삭제", notes = "성공 시 '댓글 삭제 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @DeleteMapping("/{comment_id}")
    public ControllerResponse deleteComment(@PathVariable long comment_id){
        ControllerResponse response = null;

        try{
            commentService.deleteComment(comment_id);
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
                list.add(new CommentResponseDto(comment, userService.findUser(comment.getUserId())));
            }

            response = new ControllerResponse("success", list);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /*
    해당 게시글 번호의 댓글 개수 반환
     */
    @ApiOperation(value = "댓글 개수 찾기", notes = "성공 시 해당 게시글 번호의 댓글 개수 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    public int countComment(Long article_id){
        return commentService.countByArticleId(article_id);
    }


}
