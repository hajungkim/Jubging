package com.ssafy.jupging.domain.repository;

import com.ssafy.jupging.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByNickname(String nickname);

    User findByEmailAndPassword(String email, String password);

    List<User> findByNicknameContaining(String nickname);
}
