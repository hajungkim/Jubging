package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Event;
import com.ssafy.jupging.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    @Transactional
    public List<Event> findAllEvent() {
        return eventRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate"));
    }

}
