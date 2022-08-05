package com.coconet.server.repository;

import com.coconet.server.dto.NoticeDto;
import com.coconet.server.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Notice, Integer> {
    @Query("select n.title from Notice n")
    List<String> findAllByTitle();

    @Query("select n.day from Notice n")
    List<String> findAllByDay();

    @Query("select n.id from Notice n")
    List<Integer> findAllById();

    @Query("select n.title from Notice n where n.day LIKE %:day%")
    List<String> findAllByTitleStartingWith(@Param("day") String day);

    @Query("select n.day from Notice n where n.day LIKE %:day%")
    List<String> findAllByDayStartingWith(@Param("day") String day);

    @Query("select n.id from Notice n where n.day LIKE %:day%")
    List<Integer> findAllByIdStartingWith(@Param("day") String day);
}