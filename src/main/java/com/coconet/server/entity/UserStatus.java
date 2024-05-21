package com.coconet.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserStatus {

    @Id
    @Column(name = "user_id")
    private int num;

    private int status;

}
