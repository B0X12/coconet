package com.coconet.server.controller;

import com.coconet.server.dto.AuthDto;
import com.coconet.server.dto.TokenDto;
import com.coconet.server.entity.Users;
import com.coconet.server.jwt.JwtTokenProvider;
import com.coconet.server.repository.UserRepository;
import com.coconet.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class JwtController {

    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    /**
     refresh token으로 access token 새로 발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<AuthDto> reissueToken(@RequestHeader("Jwt_Access_Token") String accessToken,
                                                @RequestBody Map<String, String> refreshTokenMap)
    {
        // Access Token에 담긴 유저 정보를 가져옴
        String email = tokenProvider.getUserEmail(accessToken);

        TokenDto tokenDto = authService.relssueAccessToken(accessToken, refreshTokenMap.get("refreshToken"), email);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Jwt_Access_Token", tokenDto.getAccessToken());
        responseHeaders.add("Jwt_Refresh_Token", tokenDto.getRefreshToken());

        Users user = userRepository.findByEmail(email);
        AuthDto authDto = new AuthDto(user.getName(), "true");

        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(authDto);
    }
}
