package com.coconet.server.controller;

import com.coconet.server.define.LogTag;
import com.coconet.server.dto.CertificationDto;
import com.coconet.server.entity.Users;
import com.coconet.server.exception.UserNotFoundException;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.service.CertificationService;
import com.coconet.server.service.CustomUserDetailsService;
import com.coconet.server.service.LogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class CertificationController {

    private final UserRepository userRepository;
    private final LogService logService;
    private final CustomUserDetailsService customUserDetailsService;
    private final CertificationService certificationService;
    private final LogTag logTag;

    /**
     * todolist 추가
     */
    @GetMapping("/sendSMS")
    public ResponseEntity<CertificationDto> sendSMS(@RequestParam("name") String name, @RequestParam("phone") String phone) {
        // name으로 정보 찾아서 phone 정보 일치하는지 확인

        Users user = userRepository.findByNameAndPhone(name, phone);
        Optional<Users> loginUser = Optional.ofNullable(user);

        if (loginUser.isEmpty()) {
            // String tag, boolean status, String title, String email, String type
            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_CERTIFICATION
                    , "휴대폰 인증 실패"
                    , user.getName()
                    , user.getEmail());

            throw new UserNotFoundException(String.format("사용자를 찾을 수 없습니다."));
        }
        else
        {
            Random rand = new Random();
            String authCode = "";

            for (int i = 0; i < 4; i++) {
                String ran = Integer.toString(rand.nextInt(10));
                authCode += ran;
            }

            logService.buildLog(
                    customUserDetailsService.loadAuthoritiesByUser(user)
                    , logTag.TAG_CERTIFICATION
                    , "휴대폰 인증 성공"
                    , user.getName()
                    , user.getEmail());

            certificationService.certifiedPhoneNumber(phone, authCode);

            CertificationDto certificationDto = new CertificationDto(authCode, true);
            return ResponseEntity.ok().body(certificationDto);
        }
    }
}
