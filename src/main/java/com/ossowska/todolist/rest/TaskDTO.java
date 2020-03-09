package com.ossowska.todolist.rest;

import com.ossowska.todolist.persistance.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskDTO {

    private Long id;
    private String description;
    private String status;

    public static TaskDTO fromTask(Task task) {
        TaskDTO result = new TaskDTO();
        result.id = task.getId();
        result.description = task.getDescription();
        result.status = String.valueOf(task.getStatus());
        List<TaskDTO> subtasks = task.getSubTasks().stream().map(a -> fromTask(a)).collect(Collectors.toList());
        result.subtasks = subtasks;
        return result;
    }

    private List<TaskDTO> subtasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
