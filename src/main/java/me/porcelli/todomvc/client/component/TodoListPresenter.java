package me.porcelli.todomvc.client.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;

public class TodoListPresenter {

    private final Map<Todo, TodoElementPresenter> todoElements = new LinkedHashMap<Todo, TodoElementPresenter>();
    private TodoListView view;
    private TodoListFooterPresenter todoListFooterPresenter;

    public void init( final TodoListView view,
                      final TodoListFooterPresenter todoListFooterPresenter ) {
        this.view = view;
        this.todoListFooterPresenter = todoListFooterPresenter;
        view.build();
    }

    public void addTodo( final Todo newTodo ) {
        final TodoElementPresenter _presenter = new TodoElementPresenter();
        final TodoElementView _view = new TodoElementView();
        _view.init( _presenter );
        _presenter.init( newTodo, _view, this );
        todoElements.put( newTodo, _presenter );

        view.addTodo( _presenter.getView() );
        todoListFooterPresenter.updateCount( todoElements.size() );
    }

    public void deleteTodo( final Todo todo ) {
        deleteTodo( todo, true );
    }

    public void deleteTodo( final Todo todo,
                            boolean updateCount ) {
        final TodoElementPresenter presenter = todoElements.remove( todo );
        if ( presenter != null ) {
            presenter.delete();
        }
        if ( updateCount ) {
            todoListFooterPresenter.updateCount( todoElements.size() );
        }
    }

    public IsWidget getView() {
        return view;
    }

    public void selectAll() {
        for ( final TodoElementPresenter presenter : todoElements.values() ) {
            presenter.updateStatus( Status.COMPLETED );
        }
    }

    public void unSelectAll() {
        for ( final TodoElementPresenter presenter : todoElements.values() ) {
            presenter.updateStatus( Status.ACTIVE );
        }
    }

    public void clearCompleted() {
        final Collection<Todo> delete = new ArrayList<Todo>();
        for ( Todo todo : todoElements.keySet() ) {
            if ( todo.getStatus().equals( Status.COMPLETED ) ) {
                delete.add( todo );
            }
        }
        for ( final Todo todo : delete ) {
            deleteTodo( todo, false );
        }
        todoListFooterPresenter.updateCount( todoElements.size() );
    }

    public void showOnlyActive() {
        for ( final Map.Entry<Todo, TodoElementPresenter> todoTodoElementPresenterEntry : todoElements.entrySet() ) {
            if ( todoTodoElementPresenterEntry.getKey().getStatus().equals( Status.ACTIVE ) ) {
                todoTodoElementPresenterEntry.getValue().show();
            } else {
                todoTodoElementPresenterEntry.getValue().hide();
            }
        }
    }

    public void showOnlyCompleted() {
        for ( final Map.Entry<Todo, TodoElementPresenter> todoTodoElementPresenterEntry : todoElements.entrySet() ) {
            if ( todoTodoElementPresenterEntry.getKey().getStatus().equals( Status.COMPLETED ) ) {
                todoTodoElementPresenterEntry.getValue().show();
            } else {
                todoTodoElementPresenterEntry.getValue().hide();
            }
        }
    }

    public void showAll() {
        for ( final TodoElementPresenter presenter : todoElements.values() ) {
            presenter.show();
        }
    }
}
