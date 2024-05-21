package com.coconet.server.controller;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.repository.AdminWorkTimeRepository;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.repository.UserStatusRepository;
import com.coconet.server.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@EnableScheduling
@Configuration
@RequiredArgsConstructor
public class ScheduledController {

    // 출근 시간 조회 관련
    private static AdminWorkTimeRepository adminWorkTimeRepository;

    // 상태 조회 관련
    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final Status status;

    // 로그 관련
    private final LogService logService;
    private final LogTag logTag;

    // 차트 데이터 조회 관련
    private long allUserCount = 0;
    private double workUserCount = 0; // 근무중


    @Scheduled(cron = "${coconet.worktime.crontext}") // 관리자가 지정한 출근 시간에
    public void writeLogSchedule()
    {
        boolean result = writeLog(); // 유저 출근 % 로그 기록
        if (result)
        {
            System.out.println("[COCONET]: 출근 로그를 기록하였습니다.");
        }
        else
        {
            System.out.println("[COCONET]: 출근 로그를 기록하지 못했습니다.");
        }

    }

    public boolean writeLog()
    {
        allUserCount = userRepository.count(); // 전체 유저수 조회

        workUserCount = userStatusRepository.countByStatus(status.WORK_START); // 근무중인 사용자 수 조회
        workUserCount += userStatusRepository.countByStatus(status.WORK_BREAK); // 휴식중인 사용자 수

        double workUser = (workUserCount / allUserCount) * 100;

        logService.buildLog(
                logTag.TAG_STATUS
                , "직원 " + (int)workUser + "% 출근 완료");

        return true;
    }

}