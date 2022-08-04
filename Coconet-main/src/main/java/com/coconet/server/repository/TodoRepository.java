package com.coconet.server.repository;

import com.coconet.server.entity.ApprovalData;
import com.coconet.server.entity.TodoData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoData, Integer> {

    List<TodoData> findByuserName(String name);
    TodoData findByTodo(String todo);

}