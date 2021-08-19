package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.JubgingLog;
import com.ssafy.jupging.domain.repository.JubgingLogRepository;
import com.ssafy.jupging.dto.JubgingLogResponseDto;
import com.ssafy.jupging.dto.JubgingLogSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JubgingLogService {

    private final JubgingLogRepository jubgingLogRepository;

    @Transactional
    public JubgingLog saveJubgingLog(JubgingLogSaveRequestDto requestDto) {
        JubgingLog jubgingLog = new JubgingLog();
        jubgingLog = jubgingLog.saveJubgingLog(requestDto);
        return jubgingLogRepository.save(jubgingLog);
    }

    @Transactional
    public List<JubgingLogResponseDto> findUserJubgingLog(Long userId) {
        List<JubgingLog> jubgingLogs = jubgingLogRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<JubgingLogResponseDto> result = new ArrayList<>();
        for (JubgingLog j : jubgingLogs) {
            String[] temp = j.getCreatedDate().toString().split("T");
            String[] temp2 = temp[0].split("-");
            String date = temp2[0] + "년 " + temp2[1] + "월 " + temp2[2] + "일";
            JubgingLogResponseDto responseDto
                    = new JubgingLogResponseDto(j.getUserId(), j.getDistance(), j.getTotalTime(), j.getCreatedDate(), date, j.getAddress());
            result.add(responseDto);
        }
        return result;
    }

    @Transactional
    public void deleteJubgingLog(Long jubgingId) {
        jubgingLogRepository.deleteById(jubgingId);
    }

    @Transactional
    public int countJubgingLog(LocalDateTime start, LocalDateTime end) {
        List<JubgingLog> list = jubgingLogRepository.findByCreatedDateBetween(start, end);
        return list.size();
    }
}
