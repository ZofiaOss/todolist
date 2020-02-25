package com.ossowska.todolist.rest;

import com.ossowska.todolist.persistance.Task;
import com.ossowska.todolist.persistance.TaskDao;
import com.ossowska.todolist.persistance.TodoList;
import com.ossowska.todolist.persistance.TodoListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todolists")
@Transactional
public class TodoListController {

    @Autowired
    private TaskDao taskDao;
    @Autowired
    private TodoListDao todoListDao;


    @GetMapping("")
    public List<TodoListDTO> getTodoLists() {
        return todoListDao.findAll().stream().map(TodoListDTO::fromTodoList).collect(Collectors.toList());
    }

    @PostMapping("")
    public TodoListDTO addTodoList(@RequestBody TodoList todoList) {
        return TodoListDTO.fromTodoList(todoListDao.save(todoList));
    }

    @GetMapping("/{id}")
    public TodoListDTO getTodoList(@PathVariable("id") Long id) {
        return TodoListDTO.fromTodoList(todoListDao.getOne(id));
    }

    @DeleteMapping("/{id}")
    public void deleteTodoList(@PathVariable("id") Long id) {
        todoListDao.deleteById(id);
    }

    @PutMapping("/{id}")
    public TodoListDTO updateTodoList(@PathVariable("id") Long id, @RequestBody TodoList todoList) {
        TodoList dbObject = todoListDao.getOne(id);
        dbObject.setName(todoList.getName());
        return TodoListDTO.fromTodoList(todoListDao.save(dbObject));
    }

    @GetMapping("/{id}/tasks")
    public List<TaskDTO> getTasks(@PathVariable("id") Long id) {
        return taskDao.getTasksByListId(id).stream().map(TaskDTO::fromTask).collect(Collectors.toList());
    }

    @PostMapping("/{id}/tasks")
    public TaskDTO addTask(@PathVariable("id") Long id, @RequestBody Task task) {
        TodoList list = todoListDao.getOne(id);
        task.setTodoList(list);
        return TaskDTO.fromTask(taskDao.save(task));

    }

    @GetMapping("/{id}/tasks/{taskId}")
    public TaskDTO getTask(@PathVariable("id") Long id, @PathVariable("taskId") Long taskId) {
        return TaskDTO.fromTask(taskDao.getTaskByListIdAndId(id, taskId));
    }

    @PutMapping("/{id}/tasks/{taskId}")
    public TaskDTO updateTask(@PathVariable("id") Long id, @PathVariable("taskId") Long taskId, @RequestBody Task task) {
        Task dbObject = taskDao.getTaskByListIdAndId(id, taskId);
        dbObject.setDescription(task.getDescription());
        dbObject.setStatus(task.getStatus());
        return TaskDTO.fromTask(taskDao.save(dbObject));
    }


    @DeleteMapping("/{id}/tasks/{taskId}")
    public void deleteTask(@PathVariable("id") Long id, @PathVariable("taskId") Long taskId) {
        taskDao.deleteByListIdAndId(id, taskId);

    }
}
