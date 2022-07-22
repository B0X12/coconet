package com.coconet.server.service;

import com.coconet.server.dto.LoginDto;
import com.coconet.server.dto.RelssueTokenDto;
import com.coconet.server.dto.TokenDto;
import com.coconet.server.entity.Token;
import com.coconet.server.entity.Users;
import com.coconet.server.exception.CustomException;
import com.coconet.server.jwt.JwtTokenProvider;
import com.coconet.server.repository.RefreshTokenRepository;
import com.coconet.server.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;


    public AuthService(JwtTokenProvider tokenProvider
            , AuthenticationManagerBuilder authenticationManagerBuilder
            , RefreshTokenRepository refreshTokenRepository
            , UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
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


    // Access Token 재발급
    public RelssueTokenDto relssueAccessToken(String refreshToken) {

        // Refresh Token으로 유저 email을 가져옴
        Optional<Token> token = refreshTokenRepository.findByRefreshToken(refreshToken);
        token.orElseThrow(
                () -> new CustomException("잘못된 JWT 토큰입니다.")
        );
        String findRefreshToken = token.get().getRefreshToken();

        // Refresh Token의 일치 여부 확인
        if (!(refreshToken.equals(findRefreshToken))) {
            new CustomException("JWT 토큰 정보가 일치하지 않습니다.");
        }

        // Refresh Token의 만료 여부 확인
        boolean isTokenValid = tokenProvider.validateToken(refreshToken);
        if (isTokenValid) {
            Users user = userRepository.findByEmail(token.get().getEmail());
            Optional<Users> loginUser = Optional.ofNullable(user);

            if (loginUser.isPresent()) {
                // refresh Token으로 기존의 Authentication 객체 받아와서
                // 그걸로 새 Access, Refresh 토큰 발급
                Authentication authentication = tokenProvider.getAuthenticationByRefreshToken(refreshToken);
                TokenDto tokenDto = tokenProvider.createToken(authentication);

                // 새로 발급받은 Refresh Token은 DB에 새로 저장
                Token saveToken = new Token(token.get().getEmail(), tokenDto.getRefreshToken());
                refreshTokenRepository.save(saveToken);

                RelssueTokenDto relssueTokenDto = new RelssueTokenDto(
                        authentication.getName()
                        , "true"
                        , tokenDto.getAccessToken()
                        , tokenDto.getRefreshToken()
                );

                return relssueTokenDto;
            }
        }
        return null;
    }
}