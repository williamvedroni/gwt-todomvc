package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;

@ApplicationScoped
public class TodoListFooterPresenter {

    @Inject
    private TodoListPresenter listPresenter;
    @Inject
    private TodoListFooterView view;

    @PostConstruct
    public void init() {
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
