package com.coconet.server.dto;

import com.sun.istack.Nullable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String name;

    @Nullable
    private String authResult;

    @Nullable
    private String status;

    private int userid;

    public AuthDto (String name, int userid) {
        this.name = name;
        this.userid = userid;
    }

}
