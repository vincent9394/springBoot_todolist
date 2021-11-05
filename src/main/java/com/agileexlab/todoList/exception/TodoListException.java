package com.agileexlab.todoList.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TodoListException extends RuntimeException {
    public TodoListException() {
        super("TodoList has error");

    }
}
