package com.ossowska.todolist.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskDao extends JpaRepository<Task, Long> {
    @Query("select t from Task t where t.todoList.id = :listId")
    List<Task> getTasksByListId(Long listId);

    @Query("select t from Task t where t.todoList.id = :listId and t.id = :taskId")
    Task getTaskByListIdAndId(Long listId, Long taskId);

    @Modifying
    @Query("delete from Task t where t.todoList.id = :listId and t.id = :taskId")
    void deleteByListIdAndId(Long listId, Long taskId);
}
