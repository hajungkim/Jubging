package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.domain.repository.UserRepository;
import com.ssafy.jupging.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void updateUser(UserUpdateRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        } else {
            user.updateUser(requestDto);
        }
    }

    @Transactional
    public void deleteUser(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("유저 정보가 없습니다.");
        } else {
            userRepository.deleteByEmail(email);
        }
    }

}
