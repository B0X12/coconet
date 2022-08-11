package com.coconet.server.repository;

import com.coconet.server.entity.AdminWorkTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AdminWorkTimeRepository extends JpaRepository<AdminWorkTime, Integer> {

    @Query("select a.title from AdminWorkTime a")
    List<String> findByAllTitle();

    AdminWorkTime findByTitle(String title);


    // 근무일, 근무시간 조회
    @Query("select a.value from AdminWorkTime a "
            + "where a.title = :title")
    String findValueByTitle(@Param("title") String title);


    @Transactional
    @Modifying
    @Query("update AdminWorkTime a set a.value = :modify_value where a.title = :title")
    void updateValueTitle(@Param("modify_value") String value, @Param("title") String title);

    @Query("select a.value from AdminWorkTime a")
    List<String> findByAllValue();
}