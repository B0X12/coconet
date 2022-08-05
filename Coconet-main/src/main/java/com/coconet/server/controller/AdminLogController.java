package com.coconet.server.controller;

import com.coconet.server.entity.Users;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.service.ReadFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class AdminLogController {

    private final ReadFileService readFileService;
    private final UserRepository userRepository;

    @GetMapping("/logs/admin") // ADMIN 페이지에서 관리자 로그 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public void adminLogInfo(HttpServletRequest request
            , HttpServletResponse response)
    {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            String jsonString = readFileService.getFileContents("admin_log.log");

            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/logs/user") // ADMIN 페이지에서 관리자 로그 조회
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public void userLogInfo(@RequestParam("department") String department
            , @RequestParam("position") String position
            , @RequestParam("username") String name
            , HttpServletRequest request
            , HttpServletResponse response)
    {
        Users findUser = userRepository.findByDepartmentAndPositionAndName(department, position, name);
        String findUserEmail = findUser.getEmail();

        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");

            String jsonString = readFileService.getFileContents("user_log.log", findUserEmail);
/*
            Gson gson = new Gson();
            LogDataDto logDataDto = gson.fromJson(jsonString, LogDataDto.class);

            추후 Jackson 라이브러리 써서 Json으로 반환하기...
            String을 application/json으로 보내는거 말고
*/
            response.getWriter().write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
