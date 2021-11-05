package com.agileexlab.todoList.controller;

import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.repository.TodoListRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodoListControllerTest {
    String url = "/todos";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private TodoListRepository todoListRepository;


    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        todoListRepository.deleteAll();
    }


    @Test
    void should_return_all_todoItem_when_execute_findAllTodoList_given_two_todoItem() throws Exception {
        //given
        TodoList todoItem1 = todoListRepository.save(new TodoList("text 1", false));
        TodoList todoItem2 = todoListRepository.save(new TodoList("text 2", false));
        //when
        ResultActions resultActions = this.mockMvc.perform(get("/todos"));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(todoItem1.getId()));
    }

    @Test
    void should_return_added_todoItem_when_execute_addTodoItem_given_one_todoItem_info() throws Exception {
        //given
        TodoList newTodoItem = todoListRepository.save(new TodoList("text 1", false));

        //when
        ResultActions resultActions = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newTodoItem)));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newTodoItem.getId() + 1))
                .andExpect(jsonPath("$.text").value(newTodoItem.getText()))
                .andExpect(jsonPath("$.done").value((newTodoItem.isDone())));
    }

    @Test
    void should_return_updated_todoItem_when_execute_updateTodoItem_given_one_todoItem_info() throws Exception {
        //given
        TodoList originTodoList = todoListRepository.save(new TodoList("text1", false));
        TodoList updatedTodoList = originTodoList;
        updatedTodoList.setDone(true);
        String id = "/" + originTodoList.getId();
        System.out.println("-----------------------------------------------------------ID is :" + id);
        //when
        ResultActions resultActions = mockMvc.perform(put(url + id).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTodoList)));

        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updatedTodoList.getId()))
                .andExpect(jsonPath("$.text").value(updatedTodoList.getText()))
                .andExpect(jsonPath("$.done").value((updatedTodoList.isDone())));
    }

    @Test
    void should_return_delete_one_todoItem_when_execute_deleteTodoItem_given_one_todoItem_id() throws Exception {
        //given
        TodoList deletedTodoList = todoListRepository.save(new TodoList("text1", false));
        String id = "/" + deletedTodoList.getId();
        //when
        ResultActions resultActions = mockMvc.perform(delete(url + id));

        //then
        resultActions
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_todoList_page_when_execute_findByPageAndPageSize_given_page_and_page_size() throws Exception {
        //given
        TodoList TodoList1 = todoListRepository.save(new TodoList("text1", false));
        TodoList TodoList2 = todoListRepository.save(new TodoList("text1", false));
        TodoList TodoList3 = todoListRepository.save(new TodoList("text1", false));
        TodoList TodoList4 = todoListRepository.save(new TodoList("text1", false));
        String page = "?page=1&size=2";
        //when
        ResultActions resultActions = mockMvc.perform(get(url + page));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(TodoList3.getId()))
                .andExpect(jsonPath("$.content[0].text").value(TodoList3.getText()))
                .andExpect(jsonPath("$.content[0].done").value(TodoList3.isDone()))
                .andExpect(jsonPath("$.content[1].id").value(TodoList4.getId()))
                .andExpect(jsonPath("$.content[1].text").value(TodoList4.getText()))
                .andExpect(jsonPath("$.content[1].done").value(TodoList4.isDone()))

        ;
    }
}