package com.agileexlab.todoList.controller;

import com.agileexlab.todoList.dto.TodoListRequest;
import com.agileexlab.todoList.dto.TodoListResponse;
import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.mapper.TodoListMapper;
import com.agileexlab.todoList.service.TodoListService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/todos")
public class TodoListController {

    private final TodoListMapper todoListMapper;

    private final TodoListService todoListService;

    public TodoListController(TodoListMapper todoListMapper, TodoListService todoListService) {
        this.todoListMapper = todoListMapper;
        this.todoListService = todoListService;
    }

    @GetMapping
    public List<TodoListResponse> findAllTodoList(){
        return this.todoListService.findAll()
                .stream()
                .map(todoItem -> todoListMapper.toResponse(todoItem))
                .collect(Collectors.toList());
    }

    @PostMapping
    public TodoListResponse addTodoItem(@RequestBody TodoListRequest todoListRequest){
        TodoList todoList = todoListService.addTodoItem(todoListMapper.toEntity(todoListRequest));
        return todoListMapper.toResponse(todoList);
    }

    @PutMapping("/{id}")
    public TodoListResponse updateTodoItem(@PathVariable Integer id, @RequestBody TodoList updatedTodoList){
        TodoList todoList = todoListService.updateTodoItem(id,updatedTodoList);
        return todoListMapper.toResponse(todoList);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public TodoListResponse deleteTodoItem(@PathVariable Integer id) {
        return todoListMapper.toResponse(todoListService.deleteEmployee(id));
    }

}
