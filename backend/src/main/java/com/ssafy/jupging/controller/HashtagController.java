package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.Hashtag;
import com.ssafy.jupging.dto.ArticleResponseDto;
import com.ssafy.jupging.dto.HashtagSaveRequestDto;
import com.ssafy.jupging.service.ArticleService;
import com.ssafy.jupging.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    private final HashtagService hashtagService;
    private final ArticleService articleService;

    /**
     * 해시태그 등록
     * @param requestDto - Content, article_id
     * @return
     */
    @PostMapping("/test")
    public ControllerResponse saveHashtag(@RequestBody HashtagSaveRequestDto requestDto){
        ControllerResponse response = null;

        String content = requestDto.getContent();
        Long article_id = requestDto.getArticleId();

        try {
            Pattern pattern = Pattern.compile("\\#[0-9a-zA-Z가-힣]*");
            Matcher matcher = pattern.matcher(content);

            //해쉬태그 추출 -> 저장
            while(matcher.find()) {
                String tag = matcher.group().replace("#", "");
                System.out.println(tag);
                Hashtag hashtag = Hashtag.saveHashtag(tag, article_id);
                hashtagService.saveHashtag(hashtag);
            }

            response = new ControllerResponse("success", "해시태그 등록 성공");
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    /**
     * 해시태그 수정 (게시글 수정하면 다시 등록)
     * @param requestDto
     * @return
     */
    @PutMapping("/test2")
    public ControllerResponse updateHashtag(@RequestBody HashtagSaveRequestDto requestDto){
        ControllerResponse response = null;

        String content = requestDto.getContent();
        Long article_id = requestDto.getArticleId();

        hashtagService.deleteHashtag(article_id);

        try {
            Pattern pattern = Pattern.compile("\\#[0-9a-zA-Z가-힣]*");
            Matcher matcher = pattern.matcher(content);

            //해쉬태그 추출 -> 저장
            while(matcher.find()) {
                String tag = matcher.group().replace("#", "");
                System.out.println(tag);
                Hashtag hashtag = Hashtag.saveHashtag(tag, article_id);
                hashtagService.saveHashtag(hashtag);
            }

            response = new ControllerResponse("success", "해시태그 수정 성공");
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    @GetMapping("/hashlist/{hashtag}")
    public ControllerResponse findHashtag(@PathVariable String hashtag){
        ControllerResponse response = null;

        try {
            List<Hashtag> hashtagList = hashtagService.findAllHashtag(hashtag);
            List<String> list = new ArrayList<>();
            for(Hashtag hash : hashtagList){
                list.add(hash.getContent());
            }
            response = new ControllerResponse("success", list);
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    @GetMapping("/articlelist/{hashtag}")
    public ControllerResponse findArticleListByHashtag(@PathVariable String hashtag){
        ControllerResponse response = null;

        try {
            List<Hashtag> hashtagList = hashtagService.findHashtag(hashtag);
            List<ArticleResponseDto> list = new ArrayList<>();

            for(Hashtag hash : hashtagList){
                Article article = articleService.findByArticleId(Math.toIntExact(hash.getArticleId()));
                list.add(new ArticleResponseDto(article));
            }
            response = new ControllerResponse("success", list);
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

}
