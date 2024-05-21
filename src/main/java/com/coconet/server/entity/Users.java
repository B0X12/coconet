package com.coconet.server.entity;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Users {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가되는 PK
    private int num;

    private String department;  //부서
    private String position;    //직급
    private String name;        //이름

    @Column(name = "email", unique = true)
    private String email;       //이메일

    @Column(name = "phone", unique = true)
    private String phone;       //전화번호

    private String birthdate;   //생년월일
    private String password;    //비밀번호

    @Nullable
    private String andnum;      //안드로이드 번호

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}