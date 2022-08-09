package com.coconet.server.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private Logger userLogger = LoggerFactory.getLogger("userLog");
    private Logger adminLogger = LoggerFactory.getLogger("adminLog");

    private InetAddress local;


    public String getIp()
    {
        try {
            local = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return local.getHostAddress();
    }

    public String getDate()
    {
        // 현재 날짜&시간
        LocalDateTime now = LocalDateTime.now();

        // formatting
        String formatedNow = now.format(DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm:ss"));

        return formatedNow;
    }

    // Logger logger = LoggerFactory.getLogger("noticeLog");
    public boolean buildLog(List<GrantedAuthority> authority, String tag, String title, String name, String email, String department)
    {
        String ip = getIp();
        String date = getDate();

        if (authority.get(0).getAuthority().equals("ROLE_USER")) // 일반 사용자일 경우
        {
            // {tag:Login, title:로그인 성공, name:정재훈, email:jjh@naver.com, date:2022. 08. 05. 21:02:09}
            userLogger.info("{tag:{}, title:{}, name:{}, email:{}, department:{}, date:{}}"
                , tag
                , title
                , name
                , email
                , department
                , date);
            return true;
        }
        else if (authority.get(0).getAuthority().equals("ROLE_ADMIN")) // 관리자일 경우
        {
            // {ip:192.168.57.1, tag:Login, title:로그인 성공, email:admin, date:2022. 08. 05. 21:08:29}
            adminLogger.info("{ip:{}, tag:{}, title:{}, email:{}, date:{}}"
                    , ip
                    , tag
                    , title
                    , email
                    , date);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 출근 시간, 출근 사용자 퍼센트 기록용 메소드
     */
    public boolean buildLog(String tag, String title)
    {
        String date = getDate();

        userLogger.info("{tag:{}, title:{}, date:{}}"
                , tag
                , title
                , date);
        return true;

    }
}
