package com.coconet.server.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String name;
    private String authResult;

    // JWT 관련
    //private String AccessToken;
    //private String RefreshToken;

}
