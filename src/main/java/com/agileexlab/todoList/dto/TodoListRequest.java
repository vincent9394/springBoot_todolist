package com.agileexlab.todoList.dto;

public class TodoListRequest {
    private String text;
    private boolean done;

    public TodoListRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
