package com.ssafy.gupging.domain.repository;

import com.ssafy.gupging.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
