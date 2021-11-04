package com.agileexlab.todoList.service;

import com.agileexlab.todoList.entity.TodoList;
import com.agileexlab.todoList.repository.TodoListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class TodoListServiceTest {
    @Mock
    TodoListRepository todoListRepository;

    @InjectMocks
    TodoListService todoListService;

    @Test
    void should_return_all_todoItem_when_execute_findAllTodoList_given_two_todoItem() {

        //given
        List<TodoList> todoLists = Arrays.asList(
                new TodoList("text 1", true),
                new TodoList("text 2", true)
        );
        when(todoListRepository.findAll()).thenReturn(todoLists);
        //when
        List<TodoList> actual = todoListService.findAll();

        //then
        assertEquals(todoLists, actual);
    }

    @Test
    void should_return_added_todoItem_when_execute_addTodoItem_given_one_todoItem_info() {
        //given
        TodoList todoList = new TodoList("text1", true);
        when(todoListRepository.save(todoList)).thenReturn(todoList);

        //when
        TodoList actual = todoListService.addTodoItem(todoList);

        //then
        assertEquals(todoList, actual);
    }

    @Test
    void should_return_updated_todoItem_when_execute_updateTodoItem_given_one_todoItem_info() {
        //given
        TodoList todoList = new TodoList("text1", true);
        when(todoListRepository.findById(any())).thenReturn(Optional.of(todoList));

        TodoList updateInfo = new TodoList("text2", true);
        TodoList updated = new TodoList(1, "text2", true);

        when(todoListRepository.save(any(TodoList.class))).thenReturn(updated);

        //when
        TodoList actual = todoListService.updateTodoItem(1, updateInfo);

        //then
        assertEquals(1, actual.getId());
        assertEquals("text2", actual.getText());
        assertEquals(true, actual.isDone());

    }
    //TODO delete function failed
    @Test
    void should_return_delete_one_todoItem_when_execute_deleteTodoItem_given_one_todoItem_id() {
        //given
        TodoList todoList = todoListRepository.save(new TodoList("text1",true));
        doNothing().when(todoListRepository).getById(1);

        //when
        todoListService.deleteTodoItem(1);

        //then
        verify(todoListRepository, times(1)).deleteById(1);
    }


}