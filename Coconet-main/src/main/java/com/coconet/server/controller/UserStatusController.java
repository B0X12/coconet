package com.coconet.server.controller;

import com.coconet.server.define.LogTag;
import com.coconet.server.define.Status;
import com.coconet.server.entity.UserStatus;
import com.coconet.server.entity.Users;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.repository.UserStatusRepository;
import com.coconet.server.service.CustomUserDetailsService;
import com.coconet.server.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class UserStatusController {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;

    // WORK_START = 10;
    // WORK_BREAK = 11;
    // WORK_FINISH = 12;
    private final Status status;

    // 로그 관련
    private final CustomUserDetailsService customUserDetailsService;
    private final LogService logService;
    private final LogTag logTag;


    /**
     상태 업데이트
     */
    @PostMapping("/status")
    public ResponseEntity updateStatus(@RequestBody UserStatus userStatus)
    {
        Users user = userRepository.findByNum(userStatus.getNum());
        Optional<Users> loginUser = Optional.ofNullable(user);
        UserStatus findUserStatus = userStatusRepository.findByNum(user.getNum()); // Num은 FK (Users 테이블의 USER_ID)

        if (userStatus.getStatus() == findUserStatus.getStatus()) //  DB에 저장된 것과 같은 상태거나
        {
            return ResponseEntity.badRequest().build();
        }
        else if (userStatus.getStatus() != status.WORK_START
                && userStatus.getStatus() != status.WORK_OUTWORK
                && userStatus.getStatus() != status.WORK_BREAK
                && userStatus.getStatus() != status.WORK_SITETRIP
                && userStatus.getStatus() != status.WORK_DAYOFF
                && userStatus.getStatus() != status.WORK_NOTHING)  // 서버가 원하는 값이 아닐경우
        {
            return ResponseEntity.badRequest().build();
        }
        else // DB에 저장된 것과 다른 상태면
        {
            findUserStatus.setStatus(userStatus.getStatus());
            userStatusRepository.save(findUserStatus);

            // 로그 기록
            if (userStatus.getStatus() == status.WORK_START)
            {
                logService.buildLog(
                        customUserDetailsService.loadAuthoritiesByUser(user)
                        , logTag.TAG_USER_STATUS
                        , "출근"
                        , user.getName()
                        , user.getEmail()
                        , user.getDepartment());
            }
            else if (userStatus.getStatus() == status.WORK_OUTWORK) // 외근 - 관리자 결재
            {
                logService.buildLog(
                        customUserDetailsService.loadAuthoritiesByUser(user)
                        , logTag.TAG_USER_STATUS_WITH_ADMIN
                        , "외근"
                        , user.getName()
                        , user.getEmail()
                        , user.getDepartment());
            }
            else if (userStatus.getStatus() == status.WORK_SITETRIP) // 출장 - 관리자 결재
            {
                logService.buildLog(
                        customUserDetailsService.loadAuthoritiesByUser(user)
                        , logTag.TAG_USER_STATUS_WITH_ADMIN
                        , "출장"
                        , user.getName()
                        , user.getEmail()
                        , user.getDepartment());
            }
            else if (userStatus.getStatus() == status.WORK_DAYOFF) // 휴가 - 관리자 결재
            {
                logService.buildLog(
                        customUserDetailsService.loadAuthoritiesByUser(user)
                        , logTag.TAG_USER_STATUS_WITH_ADMIN
                        , "휴가"
                        , user.getName()
                        , user.getEmail()
                        , user.getDepartment());
            }
            else if (userStatus.getStatus() == status.WORK_NOTHING)
            {
                logService.buildLog(
                        customUserDetailsService.loadAuthoritiesByUser(user)
                        , logTag.TAG_USER_STATUS
                        , "퇴근"
                        , user.getName()
                        , user.getEmail()
                        , user.getDepartment());
            }
        }
        return ResponseEntity.ok("update user status: " + status.getStatus(userStatus.getStatus()));
    }

}
