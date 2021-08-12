package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Alarm;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByUserId(Long userId, Sort sort);
}
