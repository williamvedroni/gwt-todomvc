package me.porcelli.todomvc.client.events;

import me.porcelli.todomvc.client.model.Todo;

public class DeleteTodo {

    private final Todo todo;

    public DeleteTodo( Todo todo ) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
