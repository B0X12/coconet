package com.coconet.server.service;

import com.coconet.server.dto.LoginDto;
import com.coconet.server.dto.TokenDto;
import com.coconet.server.dto.UserDto;
import com.coconet.server.jwt.JwtFilter;
import com.coconet.server.jwt.JwtTokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthService {
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public AuthService(JwtTokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    } // 두 클래스 주입

    // LoginDto의 username, password로 token 생성
    public TokenDto createToken(LoginDto loginDto)
    {
        // 1. Login form의 ID/PW 를 기반으로 Authentication 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()); // email, password 기반 (차후 변경)

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate method가 실행될 때, CustomUserDetailsService 에서 만든 loadUserByUsername method가 실행됨
        // SecurityContextHolder 안에 SecurityContext 안에 Authentication가 있음 (마트료시카~)
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto createdToken = tokenProvider.createToken(authentication);

        return createdToken;
    }
}