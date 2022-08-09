package com.coconet.server.dto;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserStatusLogdataDto {

    private String title;

    @Nullable
    private String name;
    @Nullable
    private String department;

    private String date;

    @Nullable
    private String color;

    public UserStatusLogdataDto (String title, String name, String department, String date) {
        this.title = title;
        this.name = name;
        this.department = department;
        this.date = date;
    }
}