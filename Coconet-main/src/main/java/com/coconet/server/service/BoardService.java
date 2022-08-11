package com.coconet.server.service;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.entity.ChartData;
import com.coconet.server.entity.Users;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.repository.UserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

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
    private double outWorkUserCount = 0; // 외근
    private double siteTripUserCount = 0; // 출장
    private double dayOffUserCount = 0; // 휴가
    private double nothingUserCount = 0; // 출근전


    public List<ChartData> findUserStatus()
    {
        allUserCount = userRepository.count(); // 전체 유저수 조회

        workUserCount = userStatusRepository.countByStatus(status.WORK_START); // 근무중인 사용자 수 조회
        workUserCount += userStatusRepository.countByStatus(status.WORK_BREAK); // 휴식중인 사용자 수

        outWorkUserCount = userStatusRepository.countByStatus(status.WORK_OUTWORK); // 외근나간 사용자 수 조회
        outWorkUserCount += userStatusRepository.countByStatus(status.WORK_OUTWORK_START); // 외근나간 사용자 中 근무중인 사용자 수 조회
        outWorkUserCount += userStatusRepository.countByStatus(status.WORK_OUTWORK_NOTHING); // 외근나간 사용자 中 미근무중인 사용자 수 조회

        siteTripUserCount = userStatusRepository.countByStatus(status.WORK_SITETRIP); // 출장간 사용자 수 조회
        siteTripUserCount += userStatusRepository.countByStatus(status.WORK_SITETRIP_START); // 출장간 사용자 中 근무중인 사용자 수 조회
        siteTripUserCount += userStatusRepository.countByStatus(status.WORK_SITETRIP_NOTHING); // 출장간 사용자 中 미근무중인 사용자 수 조회

        dayOffUserCount = userStatusRepository.countByStatus(status.WORK_DAYOFF); // 휴가중인 사용자 수 조회

        nothingUserCount = userStatusRepository.countByStatus(status.WORK_NOTHING); // 출근전인 사용자 수 조회


        List<ChartData> chartDataList = new ArrayList<>();

        double workUser = (workUserCount / allUserCount) * 100;
        double outWorkUser = (outWorkUserCount / allUserCount) * 100;
        double siteTripUser = (siteTripUserCount / allUserCount) * 100;
        double dayOffUser = (dayOffUserCount / allUserCount) * 100;
        double nothingUser = (nothingUserCount / allUserCount) * 100;

        ChartData workData = new ChartData(1, "근무중", workUser, "#2CB0D7");
        ChartData outWorkData = new ChartData(2, "외근", outWorkUser, "#2C89DE");
        ChartData siteTripData = new ChartData(3, "출장",  siteTripUser, "#6571DC");
        ChartData dayOffData = new ChartData(4, "휴가중", dayOffUser, "#F0D828");
        ChartData nothingData = new ChartData(5, "출근전", nothingUser, "#BFC8D2");

        chartDataList.add(0, workData);
        chartDataList.add(1, outWorkData);
        chartDataList.add(2, siteTripData);
        chartDataList.add(3, dayOffData);
        chartDataList.add(4, nothingData);

        return chartDataList;
    }

}