package com.coconet.server.repository;

import com.coconet.server.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<Token, String> {

    Optional<Token> findByEmail(String email);

}
