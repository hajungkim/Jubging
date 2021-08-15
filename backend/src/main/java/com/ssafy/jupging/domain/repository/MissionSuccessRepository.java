package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Mission;
import com.ssafy.jupging.domain.entity.MissionSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionSuccessRepository extends JpaRepository<MissionSuccess, Long> {
}
