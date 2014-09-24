package me.porcelli.todomvc.client.model;

public class Todo {

    String id;
    String task;
    Status status;

    public Todo( final String id,
                 final String task,
                 final Status status ) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask( String task ) {
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus( Status status ) {
        this.status = status;
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof Todo ) ) {
            return false;
        }

        Todo todo = (Todo) o;

        if ( !id.equals( todo.id ) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
