package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Mission;
import com.ssafy.jupging.domain.entity.MissionSuccess;
import com.ssafy.jupging.domain.repository.MissionRepository;
import com.ssafy.jupging.domain.repository.MissionSuccessRepository;
import com.ssafy.jupging.dto.MissionSuccessRequestDto;
import com.ssafy.jupging.dto.MissionUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MissionSuccessService {

    private final MissionSuccessRepository missionSuccessRepository;

    @Transactional
    public void saveInit(MissionSuccess missionSuccess) {
        missionSuccessRepository.save(missionSuccess);
    }

    @Transactional
    public void updateMission(MissionSuccessRequestDto requestDto) {
        MissionSuccess missionSuccess = missionSuccessRepository.findById(requestDto.getUserId()).orElseThrow(() -> new IllegalArgumentException("유저 정보가 없습니다."));
        missionSuccess.updateMissionSuccess(requestDto);
    }

    @Transactional
    public MissionSuccess findUserMission(Long userId) {
        MissionSuccess missionSuccess = missionSuccessRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("미션 정보가 없습니다."));
        return missionSuccess;
    }

}
