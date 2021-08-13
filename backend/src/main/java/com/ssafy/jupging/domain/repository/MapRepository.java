package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapRepository extends JpaRepository<Map, String> {

    Map findByName(String add);

    List<Map> findByCodeStartingWith(String code);
}
