package com.coconet.server.controller;

import com.coconet.server.define.NoticeType;
import com.coconet.server.dto.AuthDto;
import com.coconet.server.dto.TokenDto;
import com.coconet.server.dto.UserDto;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.dto.LoginDto;
import com.coconet.server.entity.Users;
import com.coconet.server.service.AuthService;
import com.coconet.server.service.LogService;
import com.coconet.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class UserJpaController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final LogService logService;
    private final NoticeType noticeDefine;
    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();


    /**
     조회
     */
    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public List<Users> retrieveAllUsers() {
        return userRepository.findAll();
    }   // 전체 사용자 조회

    @GetMapping("/log")
    public boolean logTest() {

        return logService.noticeLog(noticeDefine.WORK_START, "개발팀 김사원 출근", "2022-07-18");

    }   // 전체 사용자 조회



    @GetMapping("/users/{num}")
    @PreAuthorize("hasAnyRole('ADMIN')") // ADMIN 권한만 조회 가능
    public Users retrieveUser(@PathVariable int num)
    {
        Optional<Users> users = userRepository.findById(num);

        if (users.isEmpty())
        {
            throw new UserNotFoundException(String.format("ID[%s] not found", num));
        }   // 데이터가 존재하지않으면

        return users.get();
    }   // 사용자 1명 조회


    /**
     가입
     */
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }


    /**
     로그인
     */
    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody LoginDto loginDto)
    {
        Users user = userRepository.findByEmail(loginDto.getEmail());
        Optional<Users> loginUser = Optional.ofNullable(user);

        // 김현빈을 위한 header 세팅
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Access-Control-Allow-Origin", "*");
        responseHeaders.add("Access-Control-Allow-Credentials", "true");

        // JWT 토큰 생성
        TokenDto tokenDto = authService.createToken(loginDto);

        /**
         * DB에서 유저 조회
         */

        if (loginUser.isEmpty()) {
            throw new UserNotFoundException(String.format("사용자를 찾을 수 없습니다."));
        }
        else if ((user.getEmail().equals(loginDto.getEmail()))
                && (bcrypt.matches(loginDto.getPassword(), user.getPassword())))
                    // 첫 번째 인자(plain)와 두 번째 인자(encrypt)의 값이 동일한지 확인
        {
            String name = user.getName();
            AuthDto returnData = new AuthDto(name, "true");
            TokenDto returnToken = new TokenDto(tokenDto.getAccessToken(), tokenDto.getRefreshToken());
            responseHeaders.add("JWT-Access-Token", tokenDto.getAccessToken());
            responseHeaders.add("JWT-Refresh-Token", tokenDto.getRefreshToken());

            return ResponseEntity.ok()
                    .headers(responseHeaders)
                    .body(returnData);
        }
        else {
            throw new UserNotFoundException(String.format("입력된 정보가 틀렸습니다."));
        }
    }

    @DeleteMapping("/users/{num}")
    public void deleteUser(@PathVariable int num) {
        userRepository.deleteById(num);
    }   //사용자 삭제

    @PostMapping("/users")
    public ResponseEntity<Users> createUser(@Validated @RequestBody Users users) {
        Users savedUser = userRepository.save(users);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{num}")
                .buildAndExpand(savedUser.getNum())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
