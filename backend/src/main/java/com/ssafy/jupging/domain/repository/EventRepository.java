package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
