package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.dto.ArticleResponseDto;
import com.ssafy.jupging.dto.ArticleSaveRequestDto;
import com.ssafy.jupging.dto.ArticleUpdateRequestDto;
import com.ssafy.jupging.service.ArticleService;
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

    /**
     * 게시글 등록
     * @param requestDto (content, user_id)
     * @return 등록된 게시글
     */
    @PostMapping
    public ControllerResponse saveArticle(@RequestBody ArticleSaveRequestDto requestDto){
        ControllerResponse response = null;

        //해쉬태그
        String content = requestDto.getContent();
        Pattern pattern = Pattern.compile("\\#[0-9a-zA-Z가-힣]*");
        Matcher matcher = pattern.matcher(content);
        while(matcher.find()){
            //System.out.println(matcher.group());
            String tag = matcher.group().replace("#", "");
            System.out.println(tag);
        }

        try {
            Article article = Article.saveArticle(requestDto);
            articleService.save(article);

            response = new ControllerResponse("success", article);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 게시글 상세 조회
     * @param article_id 게시글 번호
     * @return 게시글 내용
     */
    @GetMapping("/detail/{article_id}")
    public ControllerResponse detailArticle(@PathVariable Long article_id){
        ControllerResponse response = null;

        try{
            Article article = articleService.findByArticleId(article_id);
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);
            response = new ControllerResponse("success", articleResponseDto);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @PutMapping
    public ControllerResponse updateArticle(@RequestBody ArticleUpdateRequestDto requestDto){
        ControllerResponse response = null;

        try{
            articleService.updateArticle(requestDto);

            //수정된 게시글 리턴
            Article article = articleService.findByArticleId(requestDto.getArticleId());
            ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

            response = new ControllerResponse("success", articleResponseDto);
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{article_id}")
    public ControllerResponse deleteArticle(@PathVariable int article_id){
        ControllerResponse response = null;
        try {
            articleService.deleteArticle(article_id);
            response = new ControllerResponse("success", "게시글 삭제 성공");
        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 특정 회원의 게시글 목록을 받아오는 함수
     * @param user_id
     * @return
     */
    @GetMapping("/list/{user_id}")
    public ControllerResponse findUserArtice(@PathVariable int user_id){
        ControllerResponse response = null;

        try{
            /*
            User 정보를 받아서 먼저 유저를 찾고, 그 유저의 게시글을 찾는 방식으로 바꿔야 함(User 파트 받은 이후에 할 것)
            임시로  user_id 값으로 게시글 리스트 반환하는 것으로 해놓음
             */
            //User user =
            List<Article> articleList = articleService.findByUserId(user_id);
            List<ArticleResponseDto> responselist = articleList.stream().map(ArticleResponseDto :: new).collect(Collectors.toList());

            if(responselist.isEmpty())
                response = new ControllerResponse("success", null);
            else response = new ControllerResponse("success", responselist);
        }
        catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 메인에 띄울 게시글을 반환해주는 함수
     * @return
     */
    @GetMapping("/list")
    public ControllerResponse findAllArticle(){
        ControllerResponse response = null;

        try {
            List<Article> articleList = articleService.findTop10ByOrderByCreatedDateDesc();
            List<ArticleResponseDto> responselist = articleList.stream().map(ArticleResponseDto :: new).collect(Collectors.toList());

            response = new ControllerResponse("success", responselist);

        }catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
