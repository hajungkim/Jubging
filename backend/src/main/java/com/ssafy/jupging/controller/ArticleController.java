package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.dto.ArticleResponseDto;
import com.ssafy.jupging.dto.ArticleSaveRequestDto;
import com.ssafy.jupging.dto.ArticleUpdateRequestDto;
import com.ssafy.jupging.service.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleService articleService;
    private final HashtagController hashtagController;
    private final CommentController commentController;

    /**
     * 게시글 등록
     * @param requestDto (content, user_id)
     * @return 등록된 게시글
     */
    @ApiOperation(value = "게시글 등록", notes = "성공 시 게시글 객체 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PostMapping
    public ControllerResponse saveArticle(@RequestBody ArticleSaveRequestDto requestDto) {
        ControllerResponse response = null;

        try {
            Article article = Article.saveArticle(requestDto);
            Article savedArticle =  articleService.save(article);

            //해시태그 저장
            hashtagController.saveHashtag(savedArticle.getContent(), savedArticle.getArticleId());

            response = new ControllerResponse("success", article);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 게시글 상세 조회
     * @param article_id 게시글 번호
     * @return 게시글 내용
     */
    @ApiOperation(value = "게시글 상세 조회", notes = "성공 시 응답객체 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/detail/{article_id}")
    public ControllerResponse detailArticle(@PathVariable Long article_id){
        ControllerResponse response = null;

        try {
            Article article = articleService.findByArticleId(article_id);
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);
            int commentCnt = commentController.countComment(articleResponseDto.getArticleId());
            articleResponseDto.setCommentCnt(commentCnt);
            response = new ControllerResponse("success", articleResponseDto);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @ApiOperation(value = "게시글 수정", notes = "성공 시 응답객체 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @PutMapping
    public ControllerResponse updateArticle(@RequestBody ArticleUpdateRequestDto requestDto) {
        ControllerResponse response = null;

        try {
            articleService.updateArticle(requestDto);

            //수정된 게시글 리턴
            Article article = articleService.findByArticleId(requestDto.getArticleId());
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

            //해시태그 업데이트
            hashtagController.updateHashtag(articleResponseDto.getContent(), articleResponseDto.getArticleId());

            response = new ControllerResponse("success", articleResponseDto);
        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @ApiOperation(value = "게시글 삭제", notes = "성공 시 '게시글 삭제 성공' 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @DeleteMapping("/{article_id}")
    public ControllerResponse deleteArticle(@PathVariable Long article_id){
        ControllerResponse response = null;
        try {
            //해시태그 삭제
            hashtagController.deleteHashtag(article_id);

            //게시글 삭제
            articleService.deleteArticle(article_id);

            response = new ControllerResponse("success", "게시글 삭제 성공");
        }  catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 특정 회원의 게시글 목록을 받아오는 함수
     * @param user_id
     * @return
     */
    @ApiOperation(value = "미완성", notes = "", response = ControllerResponse.class)
    @GetMapping("/list/{user_id}")
    public ControllerResponse findUserArtice(@PathVariable Long user_id){
        ControllerResponse response = null;

        try{
            /*
            User 정보를 받아서 먼저 유저를 찾고, 그 유저의 게시글을 찾는 방식으로 바꿔야 함(User 파트 받은 이후에 할 것)
            임시로  user_id 값으로 게시글 리스트 반환하는 것으로 해놓음
             */
            List<Article> articleList = articleService.findByUserId(user_id);
            List<ArticleResponseDto> responselist = articleList.stream().map(ArticleResponseDto :: new).collect(Collectors.toList());

            if(responselist.isEmpty())
                response = new ControllerResponse("success", null);
            else response = new ControllerResponse("success", responselist);
        }
        catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 메인에 띄울 게시글을 반환해주는 함수
     * @return
     */
    @ApiOperation(value = "모든 게시글 찾기", notes = "메인에 띄울 모든 게시글 리스트 반환 / 실패 시 에러메세지 출력", response = ControllerResponse.class)
    @GetMapping("/list")
    public ControllerResponse findAllArticle() {
        ControllerResponse response = null;

        try {
            List<Article> articleList = articleService.findTop10ByOrderByCreatedDateDesc();
            List<ArticleResponseDto> responselist = articleList.stream().map(ArticleResponseDto :: new).collect(Collectors.toList());

            response = new ControllerResponse("success", responselist);

        } catch (Exception e) {
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}