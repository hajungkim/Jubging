package com.ssafy.jupging.service;

import com.ssafy.jupging.domain.entity.Map;
import com.ssafy.jupging.domain.repository.MapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MapService {

    private final MapRepository mapRepository;

    @Transactional
    public void updateCountMap(String add1, String add2) {
        String code = mapRepository.findByName(add1).getCode();
        List<Map> mapList = mapRepository.findByCodeStartingWith(code);
        String searchCode = "";
        for (Map map : mapList) {
            if (map.getName().equals(add2)) {
                searchCode = map.getCode();
            }
        }
        Map updateMap = mapRepository.findById(searchCode).orElseThrow(() -> new IllegalArgumentException("지역 정보가 없습니다."));
        updateMap.updateMap();
    }

    @Transactional
    public List<Map> findAllMap() {
        return mapRepository.findAll();
    }
}
