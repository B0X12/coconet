package com.coconet.server.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

    private Logger logger = LoggerFactory.getLogger("noticeLog");

    private String TAG_MAIN = "[NOTICE]";

    public boolean noticeLog (int noticeType, String noticeTitle, String date)
    {
        logger.info("{} {} ::: {} ::: {}", TAG_MAIN, noticeType, noticeTitle, date);
        return true;
    }
}
