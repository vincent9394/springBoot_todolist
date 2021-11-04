package com.agileexlab.todoList.controller;

import com.agileexlab.todoList.dto.TodoListResponse;
import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.mapper.TodoListMapper;
import com.agileexlab.todoList.service.TodoListService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.stream.Collectors;


//@Configuration
//@EnableWebMvc                 implements WebMvcConfigurer
@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "http://localhost:3000", maxAge =3600)
public class TodoListController {

//    @Override
//    public void addCorsMappings(CorsRegistry registry){
//        registry.addMapping("/**")
//                .allowedMethods("GET","POST");
//    }

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


}
