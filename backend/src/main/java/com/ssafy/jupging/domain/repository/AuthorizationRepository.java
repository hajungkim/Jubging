package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {
    Authorization findByEmail(String email);
}
