package com.coconet.server.controller;

import com.coconet.server.dto.RelssueTokenDto;
import com.coconet.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/coconet")
@RequiredArgsConstructor
public class JwtController {

    private final AuthService authService;

    /**
     refresh token으로 access token 새로 발급
     */
    @PostMapping("/reissue")
    public ResponseEntity<String> reissueToken(@RequestBody Map<String, String> refreshTokenMap)
    {
        String refreshToken = refreshTokenMap.get("refreshToken");

        // 새 토큰 발급
        RelssueTokenDto relssueTokenDto = authService.relssueAccessToken(refreshToken);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Jwt_Access_Token", relssueTokenDto.getAccessToken());
        responseHeaders.add("Jwt_Refresh_Token", relssueTokenDto.getRefreshToken());

        return ResponseEntity.ok().headers(responseHeaders).body("");
    }
}
