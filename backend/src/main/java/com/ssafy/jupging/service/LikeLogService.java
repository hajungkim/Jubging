package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Article;
import com.ssafy.jupging.domain.entity.LikeLog;
import com.ssafy.jupging.domain.repository.ArticleRepository;
import com.ssafy.jupging.domain.repository.LikeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LikeLogService {

    private final LikeLogRepository likeLogRepository;
    private final ArticleRepository articleRepository;

    @Transactional
    public LikeLog saveLikeLog(LikeLog likeLog) {
            return likeLogRepository.save(likeLog);
    }

    @Transactional
    public void deleteLikeLog(Long userId, Long articleId) {
        likeLogRepository.deleteByUserIdAndArticleId(userId, articleId);
    }

    @Transactional
    public List<LikeLog> findLikeLog(Long userId) {
        return likeLogRepository.findAllByUserId(userId);
    }

    @Transactional
    public List<LikeLog> findUserLikeList(long article_id) {
        return likeLogRepository.findAllByArticleId(article_id);
    }

    @Transactional
    public void updateLikecnt(boolean like, int cnt, Long articleId) {

        Optional<Article> article = articleRepository.findByArticleId(articleId);

        article.ifPresent(article1 -> {
            int count = article1.getLikeCnt();
            if (like==true) {
                article1.setLikeCnt(count+1);
            } else {
                article1.setLikeCnt(count-1);
            }

            articleRepository.save(article1);
        });

    }
}
