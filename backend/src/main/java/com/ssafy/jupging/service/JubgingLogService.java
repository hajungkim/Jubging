package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.JubgingLog;
import com.ssafy.jupging.domain.repository.JubgingLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class JubgingLogService {

    private final JubgingLogRepository jubgingLogRepository;

    @Transactional
    public JubgingLog saveJubgingLog(JubgingLog jubgingLog) {
        return jubgingLogRepository.save(jubgingLog);
    }

    @Transactional
    public List<JubgingLog> findUserJubgingLog(Long userId) {
        return jubgingLogRepository.findByUserId(userId);
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
