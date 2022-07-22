package com.coconet.server.controller;

import com.coconet.server.define.NoticeType;
import com.coconet.server.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;
    private final NoticeType noticeDefine;

    /**
     로그
     */
    @GetMapping("/log")
    public boolean logTest() {

        return logService.noticeLog(noticeDefine.WORK_START, "개발팀 김사원 출근", "2022-07-18");
    }   // 전체 사용자 조회

}
