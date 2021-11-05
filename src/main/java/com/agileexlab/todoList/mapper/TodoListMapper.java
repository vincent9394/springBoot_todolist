package com.agileexlab.todoList.mapper;

import com.agileexlab.todoList.dto.TodoListRequest;
import com.agileexlab.todoList.dto.TodoListResponse;
import com.agileexlab.todoList.entity.TodoList;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoListMapper {
    public TodoList toEntity(TodoListRequest todoListRequest) {
        TodoList todoList = new TodoList();

        BeanUtils.copyProperties(todoListRequest, todoList);

        return todoList;
    }

    public TodoListResponse toResponse(TodoList todoList) {
        TodoListResponse todoListResponse = new TodoListResponse();

        BeanUtils.copyProperties(todoList, todoListResponse);

        return todoListResponse;
    }
}
