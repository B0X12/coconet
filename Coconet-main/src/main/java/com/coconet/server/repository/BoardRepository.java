package com.coconet.server.repository;

import com.coconet.server.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Notice, Integer> {
}