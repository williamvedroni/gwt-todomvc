package me.porcelli.todomvc.client.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

@ApplicationScoped
public class TodoListPresenter {

    @Inject
    private TodoListView view;
    @Inject
    private TodoListFooterPresenter todoListFooterPresenter;
    @Inject
    private SyncBeanManager beanManager;

    private final Map<Todo, TodoElementPresenter> todoElements = new LinkedHashMap<Todo, TodoElementPresenter>();

    @PostConstruct
    public void init() {
        view.build();
    }

    public void addTodo( final Todo newTodo ) {
        final TodoElementPresenter _presenter = beanManager.lookupBean( TodoElementPresenter.class ).getInstance();
        _presenter.init( newTodo );
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
