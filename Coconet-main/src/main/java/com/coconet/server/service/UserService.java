package com.coconet.server.service;

import com.coconet.server.define.Status;
import com.coconet.server.dto.UserDto;
import com.coconet.server.entity.Authority;
import com.coconet.server.entity.UserStatus;
import com.coconet.server.entity.Users;
import com.coconet.server.exception.DuplicateMemberException;
import com.coconet.server.repository.UserRepository;

import com.coconet.server.repository.UserStatusRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserStatusRepository userStatusRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Status status;

    public UserService(UserRepository userRepository
            , UserStatusRepository userStatusRepository
            , BCryptPasswordEncoder passwordEncoder
            , Status status) {
        this.userRepository = userRepository;
        this.userStatusRepository = userStatusRepository;
        this.passwordEncoder = passwordEncoder;
        this.status = status;
    }


    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        // 권한 정보 추가
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Users users = Users.builder()
                .num(userDto.getNum())
                .name(userDto.getName())
                .birthdate(userDto.getBirthdate())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode("coconet@816128531"))
                .department(userDto.getDepartment())
                .position(userDto.getPosition())
                .andnum(userDto.getAndNum())
                .authorities(Collections.singleton(authority))
                .build();

        userRepository.save(users);

        // UserStatus 테이블에 컬럼 추가
        UserStatus userStatus = UserStatus.builder()
                .num(users.getNum())
                .status(status.WORK_NOTHING)
                .build();

        userStatusRepository.save(userStatus);

        return UserDto.from(users);
    }

}