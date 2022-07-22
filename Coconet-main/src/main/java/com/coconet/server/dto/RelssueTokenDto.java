package com.coconet.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RelssueTokenDto {

    private String name;
    private String authResult;
    private String AccessToken;
    private String RefreshToken;

}
