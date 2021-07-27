package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.LikeLog;
import com.ssafy.jupging.domain.repository.LikeLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LikeLogService {

    private final LikeLogRepository likeLogRepository;

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
}
