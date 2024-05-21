package com.coconet.server.repository;

import com.coconet.server.entity.Department;
import com.coconet.server.entity.Notice;
import com.coconet.server.entity.TodoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    /**
     * 관리자 page - 사용자 로그 조회
     * 최초 접근시
     * 부서 정보 전체 반환
     */
    @Query("select d.department from Department d")
    List<String> findDepartment();

    /**
     * 관리자 page - 사용자 로그 조회
     * 부서 선택시
     * 해당 부서의 ID Return (entity 객체에 선언한 변수명으로)
     */
    @Query("select d.id from Department d "
            + "where d.department = :department")
    int findByDepartment(@Param("department") String department);
}