package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Hashtag;
import com.ssafy.jupging.domain.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    @Transactional
    public void saveHashtag(Hashtag hashtag) {
        hashtagRepository.save(hashtag);
    }

    @Transactional
    public void deleteHashtag(Long articleId) {
        hashtagRepository.deleteAllByArticleId(articleId);
    }

    @Transactional
    public List<Hashtag> findAllHashtag(String hashtag) {
        return hashtagRepository.findHashtagDistinctByContentContains(hashtag);
    }

    @Transactional
    public List<Hashtag> findHashtag(String hashtag) {
        return hashtagRepository.findAllByContent(hashtag);
    }
}
