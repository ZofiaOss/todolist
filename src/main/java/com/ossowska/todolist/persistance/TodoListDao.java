package com.ossowska.todolist.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoListDao extends JpaRepository<TodoList, Long> {
}
