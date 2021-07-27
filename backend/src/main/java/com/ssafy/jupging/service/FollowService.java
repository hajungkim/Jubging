package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Follow;
import com.ssafy.jupging.domain.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowService {

    private final FollowRepository followRepository;

    @Transactional
    public void saveFollow(Follow follow) {
        followRepository.save(follow);
    }

    @Transactional
    public List<Follow> findFollow(Long userId) {
        return followRepository.findByUserId(userId);
    }

    @Transactional
    public List<Follow> findFollower(Long userId) {
        return followRepository.findByFollowUserId(userId);
    }

    @Transactional
    public void deleteByUserIdAndFollowUserId(Long userId, Long followUserId) {
        followRepository.deleteByUserIdAndFollowUserId(userId, followUserId);
    }
}
