package me.porcelli.todomvc.client.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.events.AddTodo;
import me.porcelli.todomvc.client.events.DeleteTodo;
import me.porcelli.todomvc.client.events.UpdateCount;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;
import org.jboss.errai.ioc.client.container.SyncBeanManager;

@ApplicationScoped
public class TodoListPresenter {

    @Inject
    private TodoListView view;

    @Inject
    private SyncBeanManager beanManager;

    @Inject
    private Event<UpdateCount> updateCountEvent;

    private final Map<Todo, TodoElementPresenter> todoElements = new LinkedHashMap<Todo, TodoElementPresenter>();

    @PostConstruct
    public void init() {
        view.build();
    }

    public void addTodo( @Observes final AddTodo event ) {
        final TodoElementPresenter _presenter = beanManager.lookupBean( TodoElementPresenter.class ).getInstance();
        _presenter.init( event.getTodo() );
        todoElements.put( event.getTodo(), _presenter );

        view.addTodo( _presenter.getView() );
        updateCountEvent.fire( new UpdateCount( todoElements.size() ) );
    }

    public void deleteTodo( @Observes final DeleteTodo event ) {
        deleteTodo( event.getTodo(), true );
    }

    public void deleteTodo( final Todo todo,
                            boolean updateCount ) {
        final TodoElementPresenter presenter = todoElements.remove( todo );
        if ( presenter != null ) {
            presenter.delete();
        }
        if ( updateCount ) {
            updateCountEvent.fire( new UpdateCount( todoElements.size() ) );
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
        updateCountEvent.fire( new UpdateCount( todoElements.size() ) );
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
