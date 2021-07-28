package com.ssafy.jupging.controller;

import com.ssafy.jupging.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequiredArgsConstructor
@RestController
@RequestMapping("/hashtag")
public class HashtagController {

    private final HashtagService hashtagService;

}
