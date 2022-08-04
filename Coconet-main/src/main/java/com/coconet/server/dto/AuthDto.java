package com.coconet.server.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String name;
    private String authResult;
    private String status;

}
