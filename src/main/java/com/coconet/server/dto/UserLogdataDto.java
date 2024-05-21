package com.coconet.server.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLogdataDto {

    private String tag;
    private String title;
    private String name;
    private String email;
    private String date;

}