package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Alarm;
import com.ssafy.jupging.domain.repository.AlarmRepository;
import com.ssafy.jupging.dto.AlarmCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;

    @Transactional
    public void saveAlarm(AlarmCommentRequestDto requestDto) {
        Alarm alarm = new Alarm();
        alarm = alarm.saveAlarm(requestDto);
        alarmRepository.save(alarm);
    }

    @Transactional
    public List<Alarm> findAllAlarm(Long userId) {
        return alarmRepository.findAllByUserId(userId, Sort.by(Sort.Direction.DESC, "createdDate"));
    }
}
