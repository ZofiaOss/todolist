package com.ossowska.todolist.rest;

import com.ossowska.todolist.persistance.Task;
import com.ossowska.todolist.persistance.TodoList;

import java.util.ArrayList;
import java.util.List;

public class TodoListDTO {
    private Long id;
    private String name;
    private List<TaskDTO> tasks;

    public static TodoListDTO fromTodoList(TodoList todoList) {
        TodoListDTO result = new TodoListDTO();
        result.id = todoList.getId();
        result.name = todoList.getName();
        result.tasks = new ArrayList<>(todoList.getTasks().size());
        for (Task task : todoList.getTasks()) {
            result.tasks.add(TaskDTO.fromTask(task));
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }
}
