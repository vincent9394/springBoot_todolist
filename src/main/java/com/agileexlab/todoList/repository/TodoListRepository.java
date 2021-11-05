package com.agileexlab.todoList.repository;

import com.agileexlab.todoList.entity.TodoList;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Integer> {
    PageImpl<TodoList> findAll(Pageable pageable);
}
