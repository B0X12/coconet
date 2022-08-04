package com.coconet.server.repository;

import com.coconet.server.entity.ApprovalData;
import com.coconet.server.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalData, String> {
}