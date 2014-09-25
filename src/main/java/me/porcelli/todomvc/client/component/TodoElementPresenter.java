package me.porcelli.todomvc.client.component;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.model.Todo;

@Dependent
public class TodoElementPresenter {

    @Inject
    private View view;
    @Inject
    private TodoListPresenter todoListPresenter;

    private Todo data;

    public interface View extends IsWidget {

        void hide();

        void show();

        void build();

        void setStatus( Status status );

        void setup( TodoElementPresenter presenter );
    }

    public void init( final Todo data ) {
        this.data = data;
        this.view.setup(this);
        view.build();
    }

    public String getTask() {
        return data.getTask();
    }

    public Status getStatus() {
        return data.getStatus();
    }

    public void delete() {
        view.asWidget().removeFromParent();
        todoListPresenter.deleteTodo( data );
    }

    public IsWidget getView() {
        return view;
    }

    public void setTask( String task ) {
        this.data.setTask( task );
    }

    public void setStatus( final Status status ) {
        this.data.setStatus( status );
    }

    public void updateStatus( Status status ) {
        setStatus( status );
        view.setStatus( status );
    }

    public void hide() {
        view.hide();
    }

    public void show() {
        view.show();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) {
            return true;
        }
        if ( !( o instanceof TodoElementPresenter ) ) {
            return false;
        }

        TodoElementPresenter presenter = (TodoElementPresenter) o;

        if ( !data.equals( presenter.data ) ) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
