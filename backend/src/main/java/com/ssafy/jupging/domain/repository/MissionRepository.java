package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
