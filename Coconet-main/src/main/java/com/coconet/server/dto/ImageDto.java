package com.coconet.server.dto;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {

    @Id
    @NotNull
    private int Num;
    private String profile;

}
