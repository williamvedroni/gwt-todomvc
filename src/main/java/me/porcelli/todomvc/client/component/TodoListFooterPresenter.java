package me.porcelli.todomvc.client.component;

import com.google.gwt.user.client.ui.IsWidget;

/**
 * TODO: update me
 */
public class TodoListFooterPresenter {

    private TodoListPresenter listPresenter;
    private TodoListFooterView view;

    public void init( final TodoListPresenter listPresenter,
                      final TodoListFooterView view ) {
        this.listPresenter = listPresenter;
        this.view = view;
        view.build();
    }

    public void updateCount( int count ) {
        view.updateCount( count );
    }

    public void showAll() {
        listPresenter.showAll();
    }

    public void showOnlyActive() {
        listPresenter.showOnlyActive();
    }

    public void showOnlyCompleted() {
        listPresenter.showOnlyCompleted();
    }

    public void clearCompleted() {
        listPresenter.clearCompleted();
    }

    public IsWidget getView() {
        return view;
    }
}
