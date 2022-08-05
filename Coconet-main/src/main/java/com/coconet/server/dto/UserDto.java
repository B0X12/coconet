package com.coconet.server.dto;


import com.coconet.server.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

   // 회원가입 dto

   private int Num;

   @NotNull
   private String name;

   private String birthDate;
   private String phone;

   @NotNull
   private String email;

   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
   private String password;

   private String department;
   private String position;
   private String andNum;

   private Set<AuthorityDto> authorityDtoSet;

   public static UserDto from(Users users) {
      if(users == null) return null;

      return UserDto.builder()
              .Num(users.getNum())
              .name(users.getName())
              .birthDate(users.getBirthdate())
              .phone(users.getPhone())
              .email(users.getEmail())
              .department(users.getDepartment())
              .position(users.getPosition())
              .andNum(users.getAndnum())
              .authorityDtoSet(users.getAuthorities().stream()
                      .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                      .collect(Collectors.toSet()))
              .build();
   }
}