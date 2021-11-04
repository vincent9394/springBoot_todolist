package com.agileexlab.todoList.controller;

import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.repository.TodoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoListControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TodoListRepository todoListRepository;


    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        todoListRepository.deleteAll();
    }


    @Test
    void should_return_all_todoItem_when_execute_get_all_todoItem_given_two_todoItem() throws Exception {
        //given
        TodoList todo = new TodoList();


       TodoList todoItem1 = todoListRepository.save(new TodoList("text 1",false));
       TodoList todoItem2 = todoListRepository.save(new TodoList("text 2",false));
        //when
        ResultActions resultActions = this.mockMvc.perform(get("/todos"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(todoItem1.getId()));
    }
}