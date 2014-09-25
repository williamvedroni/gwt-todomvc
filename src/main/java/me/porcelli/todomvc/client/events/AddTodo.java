package me.porcelli.todomvc.client.events;

import me.porcelli.todomvc.client.model.Todo;

public class AddTodo {

    private Todo todo;

    public AddTodo( Todo todo ) {
        this.todo = todo;
    }

    public Todo getTodo() {
        return todo;
    }
}
