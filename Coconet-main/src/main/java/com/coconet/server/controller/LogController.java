package com.coconet.server.controller;

import com.coconet.server.service.ReadFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class LogController {

    private final ReadFileService readFileService;

    @GetMapping("/logs/admin") // ADMIN 페이지에서 관리자 로그 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public String[] adminLogInfo()
    {
        try {
            return readFileService.getLines("admin_log.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
