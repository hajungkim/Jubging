package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
}
