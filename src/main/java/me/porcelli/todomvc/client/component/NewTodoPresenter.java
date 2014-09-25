package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.events.AddTodo;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;
import me.porcelli.todomvc.client.service.IdGeneratorService;

@ApplicationScoped
public class NewTodoPresenter {

    @Inject
    private NewTodoView view;

    @Inject
    private Event<AddTodo> addTodoEvent;

    @Inject
    private IdGeneratorService service;

    @PostConstruct
    public void init() {
        view.build();
    }

    public void addNewTodo( final String text ) {
        addTodoEvent.fire( new AddTodo( new Todo( String.valueOf( service.getNext() ), text, Status.ACTIVE ) ) );
    }

    public IsWidget getView() {
        return this.view;
    }
}
