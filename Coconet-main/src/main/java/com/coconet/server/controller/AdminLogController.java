package com.coconet.server.controller;

import com.coconet.server.dto.AdminLogdataDto;
import com.coconet.server.dto.UserLogdataDto;
import com.coconet.server.entity.Users;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.service.ReadFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class AdminLogController {

    private final ReadFileService readFileService;
    private final UserRepository userRepository;

    @GetMapping("/logs/admin") // ADMIN 페이지에서 관리자 로그 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<AdminLogdataDto> adminLogInfo()
    {
        try {
            return readFileService.getAdminLog("admin_log.log");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/logs/user") // ADMIN 페이지에서 관리자 로그 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<UserLogdataDto> userLogInfo(@RequestParam("department") String department
            , @RequestParam("position") String position
            , @RequestParam("username") String name)
    {
        Users findUser = userRepository.findByDepartmentAndPositionAndName(department, position, name);
        String findUserEmail = findUser.getEmail();

        try {
            return readFileService.getUserLog("user_log.log", findUserEmail);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
