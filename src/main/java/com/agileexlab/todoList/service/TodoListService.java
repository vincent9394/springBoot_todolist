package com.agileexlab.todoList.service;

import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.repository.TodoListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> findAll(){
        return todoListRepository.findAll();
    }
}
