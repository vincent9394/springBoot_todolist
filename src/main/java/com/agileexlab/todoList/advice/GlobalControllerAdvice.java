package com.agileexlab.todoList.advice;

import com.agileexlab.todoList.exception.TodoListException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler({
            TodoListException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandle(Exception exception) {
        return new ErrorResponse(999, "Global Advice Something Not Found");
    }
}
