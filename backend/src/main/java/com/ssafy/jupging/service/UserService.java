package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.domain.repository.UserRepository;
import com.ssafy.jupging.dto.UserLoginRequestDto;
import com.ssafy.jupging.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User login(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByEmailAndPassword(userLoginRequestDto.getEmail(), userLoginRequestDto.getPassword());
        return user;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
    }

    @Transactional
    public void updateUser(Long userId, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
        user.updateUser(requestDto);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
        userRepository.deleteById(userId);
    }



}
