package com.coconet.server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminLogdataDto {

    private String ip;
    private String tag;
    private String title;
    private String email;
    private String date;

}