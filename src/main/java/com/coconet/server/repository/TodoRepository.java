package com.coconet.server.repository;

import com.coconet.server.entity.TodoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoData, Integer> {

    List<TodoData> findByUserNum(int userNum);

    @Query("select t from TodoData t where t.userNum = :userNum and t.todo = :todo")
    TodoData findByUserNumTodo(@Param("userNum") int userNum, @Param("todo") String todo);

    @Transactional
    @Modifying
    @Query("update TodoData t set t.todoCheck = :todoCheck where t.userNum = :userNum and t.todo = :todo")
    void updateCheck(@Param("todoCheck") String todoCheck, @Param("userNum") int userNum, @Param("todo") String todo);

    @Transactional
    @Modifying
    @Query("delete from TodoData t where t.userNum = :userNum and t.todo = :todo")
    void deleteTodoNum(@Param("userNum") int userNum, @Param("todo") String todo);

}