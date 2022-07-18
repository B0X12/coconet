package com.coconet.server.service;

import com.coconet.server.dto.UserDto;
import com.coconet.server.entity.Authority;
import com.coconet.server.entity.Users;
import com.coconet.server.exception.DuplicateMemberException;
import com.coconet.server.repository.UserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                .Num(userDto.getNum())
                .name(userDto.getName())
                .birthDate(userDto.getBirthDate())
                .phone(userDto.getPhone())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .team(userDto.getTeam())
                .tier(userDto.getTier())
                .andNum(userDto.getAndNum())
                .authorities(Collections.singleton(authority))
                .build();

        return UserDto.from(userRepository.save(users));
    }

}