package com.coconet.server.repository;

import com.coconet.server.entity.ChartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChartRepository extends JpaRepository<ChartData, Integer> {
}