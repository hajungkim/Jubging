package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    List<Follow> findByUserId(Long userId);

    List<Follow> findByFollowUserId(Long followUserId);
}
