package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Mission;
import com.ssafy.jupging.domain.entity.User;
import com.ssafy.jupging.domain.repository.MissionRepository;
import com.ssafy.jupging.domain.repository.UserRepository;
import com.ssafy.jupging.dto.MissionUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MissionService {

    private final MissionRepository missionRepository;

    @Transactional
    public void saveInit(Mission mission) {
        missionRepository.save(mission);
    }

    @Transactional
    public void updateMission(MissionUpdateRequestDto requestDto) {
        Mission mission = missionRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
        mission.updateMission(requestDto);
    }

    @Transactional
    public Mission findUserMission(Long userId) {
        Mission mission = missionRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("미션 정보가 없습니다."));
        return mission;
    }

}
