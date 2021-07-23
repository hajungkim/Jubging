package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
