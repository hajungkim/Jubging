package com.ssafy.jupging.controller;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.Hashtag;
import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.dto.ArticleResponseDto;
import com.ssafy.jupging.dto.HashtagSaveRequestDto;
import com.ssafy.jupging.service.ArticleService;
import com.ssafy.jupging.service.HashtagService;
import com.ssafy.jupging.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
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
    private final UserService userService;

    private final CommentController commentController;

    /**
     * 해시태그 추출하고 디비에 저장하는 함수
     */
    public void makeHashtag(String content, Long article_id){
        Pattern pattern = Pattern.compile("\\#[0-9a-zA-Z가-힣]*");
        Matcher matcher = pattern.matcher(content);

        //해쉬태그 추출 -> 저장
        while(matcher.find()) {
            String tag = matcher.group().replace("#", "");
            Hashtag hashtag = Hashtag.saveHashtag(tag, article_id);
            hashtagService.saveHashtag(hashtag);
        }
    }

    //해시태그 저장 (게시글 저장할 때 호출됨)
    public void saveHashtag(String content, Long article_id){
        makeHashtag(content, article_id);
    }

    //해시태그 수정 (게시글 수정할 때, 호출됨 / 삭제하고 다시 저장)
    public void updateHashtag(String content, Long article_id){
        hashtagService.deleteHashtag(article_id); //기존 해시태그 삭제
        makeHashtag(content, article_id); //해시태그 추출->저장
    }

    //해시태그 삭제 (게시글 삭제할 때, 호출됨)
    public void deleteHashtag(Long article_id){
        hashtagService.deleteHashtag(article_id);
    }

    /**
     * 해시태그 리스트 반환
     */
    @ApiOperation(value = "해시태그 검색" ,notes = "성공 시 검색된 모든 해시태그를 리스트로 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/hashlist/{hashtag}")
    public ControllerResponse findHashtag(@PathVariable String hashtag){
        ControllerResponse response = null;

        try {
            List<Hashtag> hashtagList = hashtagService.findAllHashtag(hashtag);
            List<String> list = new ArrayList<>();
            for(Hashtag hash : hashtagList){
                if(!list.contains(hash.getContent())) //중복되는 해시태그 값은 추가 안함
                    list.add(hash.getContent());
            }

            if (list.isEmpty()) {
                response = new ControllerResponse("success", null);
            }
            else {
                Collections.sort(list, (String s1, String s2) -> s1.length()-s2.length()); //길이순 정렬
                response = new ControllerResponse("success", list);
            }
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }
        return response;
    }

    /**
     * 검색된 해시태그를 포함한 게시글을 찾음
     */
    @ApiOperation(value = "해시태그가 포함된 게시글 찾기", notes = "성공 시 해시태그가 포함된 모든 게시글을 리스트로 반환 / 실패 시 에러메세지", response = ControllerResponse.class)
    @GetMapping("/articlelist/{hashtag}")
    public ControllerResponse findArticleListByHashtag(@PathVariable String hashtag){
        ControllerResponse response = null;

        try {
            List<Hashtag> hashtagList = hashtagService.findHashtag(hashtag);
            List<ArticleResponseDto> list = new ArrayList<>();

            for(Hashtag hash : hashtagList){
                Article article = articleService.findByArticleId(hash.getArticleId());
                ArticleResponseDto articleResponseDto = new ArticleResponseDto(article);

                int commentCnt = commentController.countComment(articleResponseDto.getArticleId());
                articleResponseDto.setCommentCnt(commentCnt);

                User user = userService.findUser(articleResponseDto.getUserId());
                articleResponseDto.setNickname(user.getNickname());
                articleResponseDto.setProfilePath( user.getProfilePath());

                List<String> hashlist = getHashList(articleResponseDto.getArticleId());
                articleResponseDto.setHashlist(hashlist);

                list.add(articleResponseDto);
            }

            if (list.isEmpty()) {
                response = new ControllerResponse("success", null);
            }
            else{
                //좋아요 순으로 정렬
                Collections.sort(list, (ArticleResponseDto o1, ArticleResponseDto o2) -> o2.getLikeCnt()-o1.getLikeCnt());
                response = new ControllerResponse("success", list);
            }
        } catch (Exception e){
            response = new ControllerResponse("fail", e.getMessage());
        }

        return response;
    }

    //articleId에 있는 해시 반환
    public List<String> getHashList(Long articleId){
        List<String> list = new ArrayList<>();

        List<Hashtag> hashtagList = hashtagService.findHashtagByArticleId(articleId);
        for(Hashtag hash : hashtagList){
            list.add(hash.getContent());
        }
        return list;
    }
}
