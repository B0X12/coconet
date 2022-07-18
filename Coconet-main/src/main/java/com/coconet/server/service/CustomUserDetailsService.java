package com.coconet.server.service;

import com.coconet.server.entity.Users;
import com.coconet.server.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
   private final UserRepository userRepository;

   public CustomUserDetailsService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   // 1. 로그인 시 DB에서 Authorities(권한) 정보와 유저 정보를 가져옴
   @Override
   @Transactional
   public UserDetails loadUserByUsername(final String email) {
      return userRepository.findOneWithAuthoritiesByEmail(email)
         .map(user -> createUser(email, user))
         .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
   }

   // 2. 1의 정보를 기반으로 userdetails.User 객체를 생성해서 return
   private org.springframework.security.core.userdetails.User createUser(String email, Users user)
   {
      List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
              .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
              .collect(Collectors.toList());

      return new org.springframework.security.core.userdetails.User(user.getName(),
              user.getPassword(),
              grantedAuthorities);
   }
}