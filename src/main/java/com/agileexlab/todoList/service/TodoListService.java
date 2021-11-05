package com.agileexlab.todoList.service;

import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.exception.TodoListException;
import com.agileexlab.todoList.repository.TodoListRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<TodoList> findAll() {
        return todoListRepository.findAll();
    }

    public TodoList addTodoItem(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoItem(Integer id, TodoList updatedTodoList) {
        TodoList originTodoList = this.todoListRepository.findById(id).orElseThrow(TodoListException::new);
        if (updatedTodoList.getText() != null) {
            originTodoList.setText(updatedTodoList.getText());
        }
        originTodoList.setDone(updatedTodoList.isDone());
        return todoListRepository.save(originTodoList);
    }

    public TodoList deleteTodoItem(Integer id) {
        Optional<TodoList> deletedTodoItem = todoListRepository.findById(id);
        todoListRepository.deleteById(id);
        return deletedTodoItem.orElse(null);
    }

    public PageImpl<TodoList> findPagingTodoLists(Pageable pageable) {
        return this.todoListRepository.findAll(pageable);
    }


}
