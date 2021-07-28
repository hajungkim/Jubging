package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Mission;
import com.ssafy.jupging.domain.repository.MissionRepository;
import com.ssafy.jupging.dto.MissionSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public void saveInit(Mission mission) {
        missionRepository.save(missionRepository.save(mission));
    }

    @Transactional
    public boolean findByUserId(Long userId) {
        Optional<Mission> user = missionRepository.findById(userId);
        // 유저 아이디가 존재하는지 확인
        if (user.isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
