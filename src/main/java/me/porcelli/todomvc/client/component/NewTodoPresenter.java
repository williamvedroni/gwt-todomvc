package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;
import me.porcelli.todomvc.client.service.IdGeneratorService;

@ApplicationScoped
public class NewTodoPresenter {

    @Inject
    private TodoListPresenter todoListPresenter;

    @Inject
    private NewTodoView view;

    @Inject
    private IdGeneratorService service;

    @PostConstruct
    public void init() {
        view.build();
    }

    public void addNewTodo( final String text ) {
        todoListPresenter.addTodo( new Todo( String.valueOf( service.getNext() ), text, Status.ACTIVE ) );
    }

    public IsWidget getView() {
        return this.view;
    }
}
