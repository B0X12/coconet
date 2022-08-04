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
    public boolean buildLog(List<GrantedAuthority> authority, String tag, boolean status, String title, String email, String type)
    {
        String ip = getIp();
        String date = getDate();

        if (authority.get(0).getAuthority().equals("ROLE_USER")) // 일반 사용자일 경우
        {
            // IP:192.168.0.1 | TAG:HOME | TITLE:신규 회원 추가 | STATUS:SUCCESS | EMAIL:box0_@naver.com | DATE:2022.07.28 | TYPE:WEB
            userLogger.info("{ip:{}, tag:{}, title:{}, status:{}, email:{}, date:{}, type:{}}"
                , ip
                , tag
                , title
                , status
                , email
                , date
                , type);
            return true;
        }
        else if (authority.get(0).getAuthority().equals("ROLE_ADMIN")) // 관리자일 경우
        {
            // IP:192.168.0.1 | TAG:HOME | TITLE:출근 | STATUS:SUCCESS | EMAIL:box0_@naver.com | DATE:2022.07.28 | TYPE:WEB
            adminLogger.info("{ip:{}, tag:{}, title:{}, status:{}, email:{}, date:{}, type:{}}"
                    , ip
                    , tag
                    , title
                    , status
                    , email
                    , date
                    , type);
            return true;
        }
        else
        {
            return false;
        }
    }
}
