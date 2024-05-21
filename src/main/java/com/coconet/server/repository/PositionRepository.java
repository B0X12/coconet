package com.coconet.server.repository;

import com.coconet.server.entity.Department;
import com.coconet.server.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends JpaRepository<Position, Integer> {

    /**
     * 관리자 page - 사용자 로그 조회
     * 부서 선택시
     * 해당 부서의 직급 정보 전체 반환
     */
    @Query("select p.position from Position p "
            + "where p.departmentId = :id")
    List<String> findByDepartmentId(@Param("id") int id);

}