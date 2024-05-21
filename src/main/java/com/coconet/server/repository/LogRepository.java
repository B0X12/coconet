package com.coconet.server.repository;

import com.coconet.server.entity.ApprovalData;
import com.coconet.server.entity.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<LogData, Integer> {
}