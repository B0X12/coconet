package com.coconet.server.repository;

import com.coconet.server.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

    UserStatus findByNum(int num);

    @Query("select u.status from UserStatus u "
            + "where u.num = :num")
    String findStatusByNum(@Param("num") int num);

    long countByStatus(int status);

}