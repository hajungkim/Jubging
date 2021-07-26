package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Comment;
import com.ssafy.jupging.domain.repository.CommentRepository;
import com.ssafy.jupging.dto.CommentMapping;
import com.ssafy.jupging.dto.CommentResponseDto;
import com.ssafy.jupging.dto.CommentSaveRequestDto;
import com.ssafy.jupging.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ControllerResponse saveComment(@RequestBody CommentSaveRequestDto commentSaveRequestDto){
        ControllerResponse response = null;

        try{
            Comment comment = Comment.saveComment(commentSaveRequestDto);
            commentService.saveComment(comment);
            response = new ControllerResponse("success", "댓글 등록 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", "댓글 등록 실패");
        }

        return response;
    }

    @DeleteMapping("/{comment_id}")
    public ControllerResponse deleteComment(@PathVariable long comment_id){
        ControllerResponse response = null;

        try{
            commentService.deleteComment(comment_id);
            response = new ControllerResponse("success", "댓글 삭제 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", "댓글 삭제 실패");
        }

        return response;
    }

    @GetMapping("/{article_id}")
    public ControllerResponse findComment(@PathVariable long article_id){
        ControllerResponse response = null;

        try{
            List<CommentMapping> commentList = commentService.findAllComment(article_id);
            List<CommentResponseDto> list = commentList
                    .stream()
                    .map(comment -> new CommentResponseDto(comment))
                    .collect(Collectors.toList());
            response = new ControllerResponse("success", list);
        }catch (Exception e){
            response = new ControllerResponse("fail", "댓글 조회 실패");
        }

        return response;
    }

    /*
    해당 게시글 번호의 댓글 개수 반환
     */
    public int countComment(@PathVariable int article_id){
        return commentService.countByArticleId(article_id);
    }


}
