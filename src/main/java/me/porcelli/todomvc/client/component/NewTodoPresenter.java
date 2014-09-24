package me.porcelli.todomvc.client.component;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;
import me.porcelli.todomvc.client.service.IdGeneratorService;

/**
 * TODO: update me
 */
public class NewTodoPresenter {

    private final IdGeneratorService service = new IdGeneratorService();
    private TodoListPresenter todoListPresenter;
    private NewTodoView view;

    public void init( final TodoListPresenter todoListPresenter,
                      final NewTodoView view ) {
        this.todoListPresenter = todoListPresenter;
        this.view = view;
        view.build();
    }

    public void addNewTodo( final String text ) {
        todoListPresenter.addTodo( new Todo( String.valueOf( service.getNext() ), text, Status.ACTIVE ) );
    }

    public IsWidget getView() {
        return this.view;
    }
}
